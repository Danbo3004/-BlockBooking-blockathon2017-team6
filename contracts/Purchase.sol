pragma solidity ^0.4.19;

///seller deposit 2x the price, buyer pay and deposit an
///equivalent amount

contract Purchase {
  uint public price;
  address public seller;
  address public buyer;
  enum State { Created, Locked, Inactive }
  mapping (address => uint) pendingWithdrawals;
  State public state;
  // Ensure that `msg.value` is an even number.
  // Division will truncate if it is an odd number
  // Check via multiplication that it wasn't an odd number.
  function Purchase() payable {
    seller = msg.sender;
    price = msg.value / 2;
    pendingWithdrawals[seller] += price;
    require((2 * price ) == msg.value);
  }
  modifier condition(bool _condition) {
    require(_condition);
    _;
  }
  modifier onlyBuyer() {
    require(msg.sender == buyer);
    _;
  }
  modifier onlySeller() {
    require(msg.sender == seller);
    _;
  }
  modifier inState(State _state) {
    require(state == _state);
    _;
  }
  event Aborted();
  event PurchaseConfirmed();
  event ItemReceived();
  ///event need to inform options: open the door, unlock utils
  /// Abort the purchase and reclaim the ether.
  /// Can only be called by the seller before
  /// the contract is locked.

  function abort()
  onlySeller
  inState(State.Created)
  {
    Aborted();
    state = State.Inactive;
    seller.transfer(this.balance);
  }
  /// Confirm the purchase as buyer.
  /// Transaction has to include `2 * value` ether.
  /// The ether will be locked until confirmReceived
  /// is called.
  function confirmPurchase()
  inState(State.Created)
  condition(msg.value == 2*price)
  payable
  {
    PurchaseConfirmed();
    buyer = msg.sender;
    pendingWithdrawals[buyer] += msg.value;
    state = State.Locked;
  }
  /// Confirm that you (the buyer) received the item.
  /// This will release the locked ether.
  function confirmReceived()
  onlyBuyer
  inState(State.Locked)
  {
    ItemReceived();
    // It is important to change the state first because
    // otherwise, the contracts called using `send` below
    // can call in again here.
    state = State.Inactive;
    // NOTE: This actually allows both the buyer and the seller to
    // block the refund - the withdraw pattern should be used.
    //buyer.transfer(value);
    //seller.transfer(this.balance);
  }
  //withdraw
  function withdraw() {
    uint amount = pendingWithdrawals[msg.sender];
    pendingWithdrawals[msg.sender] = 0;
    msg.sender.transfer(amount);
  }

}
