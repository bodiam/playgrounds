'use strict';

const NODE_ENV = process.env.NODE_ENV || 'dev';
const webpack = require('webpack');

module.exports = {
    entry: {
        home: './frontend/home',
        about: './frontend/about'
    },
    output: {
        path: __dirname + '/public',
        filename: "[name].js",
        library: "[name]"
    },

    watch: NODE_ENV === 'dev',
    watchOptions: {
        aggregateTimeout: 100
    },

    devtool: NODE_ENV === 'dev' ? 'source-map' : null,

    plugins: [
        new webpack.NoErrorsPlugin(),
        new webpack.DefinePlugin({
            NODE_ENV: JSON.stringify(NODE_ENV)
        }),
        new webpack.optimize.CommonsChunkPlugin({
            name: 'common'
        })
    ],

    module: {
        loaders: [{
            test: /\.js$/,
            loader: 'babel',
            query: {
                presets: ['es2015'],
                plugins: ['transform-runtime']
            }
        }]
    }
}
