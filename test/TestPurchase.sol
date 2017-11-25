import "truffle/Assert.sol";
import "truffle/DeployedAddresses.sol";
import "../contracts/Purchase.sol";
import "../contracts/PriceChart.sol";

contract TestPurchase {
  PriceChart chart = new PriceChart();
  

  function testWithNewPurchase(){
    Purchase p = new Purchase(chart);

  }
}
