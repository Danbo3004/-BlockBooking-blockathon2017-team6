var Purchase = artifacts.require("./Purchase");
var PriceChart = artifacts.require("./PriceChart")

module.exports = function(deployer){
  deployer.deploy(Purchase);
  deployer.deploy(PriceChart);
}
