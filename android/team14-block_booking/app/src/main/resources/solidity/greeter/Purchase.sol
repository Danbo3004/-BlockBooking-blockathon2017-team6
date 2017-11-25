pragma solidity ^0.4.0;

contract PriceChart {
  address public owner;
  bytes utilName;
  uint[] utilPrice;
  uint utilCount = 0;
  uint basePrice;

  modifier onlyOwner() {
    require(msg.sender == owner);
    _;
  }

  function PriceChart(uint _basePrice) {
    owner = msg.sender;
    basePrice = _basePrice;
  }

  function addUtil(bytes1 name, uint price)
  onlyOwner
  {
    utilName.push(name);
    utilPrice.push(price);
    utilCount++;
  }

  function getBasePrice() constant external returns (uint) {
    return basePrice;
  }

  function getPrice(uint index) constant external returns(uint){
    return utilPrice[index];
  }

  function getName(uint index) constant external returns(bytes1){
    return utilName[index];
  }

  function getNum() constant external returns (uint){
    return utilCount;
  }

  function getOwnerAddress() constant external returns (address){
    return owner;
  }

}


contract Purchase {
  uint public price;
  uint public punish;
  address public seller;
  address public buyer;
  PriceChart chart;

  enum State { Created, Locked, Inactive }
  State public state;
  function Purchase(address _priceChart) payable {
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
  event BuyMore(bytes1 name);
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
    BuyMore(chart.getName(index));
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
