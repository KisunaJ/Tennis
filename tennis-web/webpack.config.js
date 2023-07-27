const path = require('path');
const HtmlWebPackPlugin = require('html-webpack-plugin');

module.exports = {
 entry: './src/index.js',
 output: {
   path: path.resolve(__dirname, 'dist'),
   filename: 'bundle.js',
   publicPath: 'http://localhost:3000/',

 },
devServer:{
  compress: true,
  port: 3000,
  historyApiFallback: true

},
 resolve: {
   extensions: ['.js', '.jsx'],
 },

 performance: {
  maxEntrypointSize: 512000,
  maxAssetSize: 512000,
},

 module: {
   rules: [
     {
       test: /\.(js|jsx)$/,
       exclude: /node_modules/,
       use: {
         loader: 'babel-loader',
       },
     },
     {
       test: /\.html$/,
       use: [
         {
           loader: 'html-loader',
         },
       ],
     },
     {
        test: /\.css$/,
        use: ['style-loader', 'css-loader'],
       },       
   ],
 },
 plugins: [
   new HtmlWebPackPlugin({
     template: './public/index.html',
     filename: './index.html',
   }), ],
};
