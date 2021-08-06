const HtmlWebpackPlugin = require('html-webpack-plugin')
const dirTree = require('directory-tree')

function findHtmlFiles(path) {
  let htmlFiles = []
  dirTree(path, { extensions: /\.html/ }, (item) => {
    htmlFiles.push(item)
  })
  return htmlFiles
}

function generateHtmlWebpackPlugin(item) {
  let absolutePath = item.path
  let relativePath = item.path.slice(
    item.path.indexOf('/', 0) + 1,
    item.path.length
  )
  return new HtmlWebpackPlugin({
    template: absolutePath,
    filename: relativePath,
  })
}

function MultipleHtmlWebpackPlugin(path) {
  let htmlFiles = findHtmlFiles(path)
  return htmlFiles.map(generateHtmlWebpackPlugin)
}

module.exports = MultipleHtmlWebpackPlugin