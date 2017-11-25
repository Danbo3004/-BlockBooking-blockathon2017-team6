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
