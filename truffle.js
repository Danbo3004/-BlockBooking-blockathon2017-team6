Web3 = require('web3')
module.exports = {
  // See <http://truffleframework.com/docs/advanced/configuration>
  // to customize your Truffle configuration!
  rpc: {
      host: 'localhost',
      port: 8545,
  },
   networks: {
     development: {
       host: 'localhost',
       port: 8545,
       network_id: '*' // Match any network id
     },
     rinkeby: {
       network_id: 4,
       //host: 'www.blockathon.asia',//'162.255.119.203',
       //port: 8545,
       provider: new Web3.providers.HttpProvider('http://www.blockathon.asia:8545'),
       from: 0x219c11D56C823642cA4c639b57d465F05aEc83F6
     }
   }
};
