module.exports = {
  // See <http://truffleframework.com/docs/advanced/configuration>
  // to customize your Truffle configuration!
  rpc: {
      // Use the default host and port when not using rinkeby
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
       host: "http://www.blockathon.asia:8545/",
       port: 8545,
       gas: 4000000
     }
   }
};
