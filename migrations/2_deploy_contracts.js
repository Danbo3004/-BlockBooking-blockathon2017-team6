var Purchase = artifacts.require("./Purchase");
var PriceChart = artifacts.require("./PriceChart")

module.exports = function(deployer){
  deployer.deploy(PriceChart);
  deployer.deploy(Purchase);
}
