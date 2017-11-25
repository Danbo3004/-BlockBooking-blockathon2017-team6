pragma solidity ^0.4.18;

import "./PriceChart.sol";

contract Purchase {
  uint public price;
  uint public punish;
  address public seller;
  address public buyer;
  PriceChart chart;

  enum State { Created, Locked, Inactive }
  State public state;
  function Purchase(address _priceChart, bytes _utilName) payable {
    chart = PriceChart(_priceChart);
    buyer = msg.sender;
    seller = chart.getOwnerAddress();
    price = msg.value;
    punish = price/20;
    require(chart.getBasePrice() == price);
  }

  modifier condition(bool _condition) {
    require(_condition);
    _;
  }
  modifier onlyBuyer() {
    require(msg.sender == buyer);
    _;
  }
  /*modifier onlySeller() {
    require(msg.sender == seller);
    _;
  }*/
  modifier inState(State _state) {
    require(state == _state);
    _;
  }
  //Abort before arrive
  event Aborted();
  //buy more ultility
  event BuyMore(uint index);
  //purchase after the door has been unlock
  event PurchaseConfirmed();
  ///event need to inform options: open the door, unlock utils
  event RoomReceived();


  //abort before arriving
  function abort()
  onlyBuyer
  inState(State.Created)
  {
    Aborted();
    state = State.Inactive;
    seller.transfer(punish);
    //this.balance -= punish;
    selfdestruct(buyer);
  }

  //confirm arriving to unlock the door
  function confirmArriving()
  onlyBuyer
  inState(State.Created)
  payable
  {
    RoomReceived();
    state = State.Locked;
  }

  //withdraw after unlocking the door
  function buyMore(uint index)
  inState(State.Locked)
  onlyBuyer
  payable
  {
    require(chart.getPrice(index) == msg.value);
    BuyMore(index);
    price += msg.value;
  }
  /// Confirm that you (the buyer) received the item.
  /// This will release the locked ether.
  function confirmPurchase()
  onlyBuyer
  inState(State.Locked)
  {
    PurchaseConfirmed();
    // It is important to change the state first because
    // otherwise, the contracts called using `send` below
    // can call in again here.
    state = State.Inactive;
    seller.transfer(this.balance);
  }

}
