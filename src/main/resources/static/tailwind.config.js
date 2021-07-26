const colors = require('tailwindcss/colors')

module.exports = {
  purge: ['./dist/*.html'],
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
      colors: {
        amber: colors.amber,
        lime: colors.lime
      }
    },
  },
  variants: {
    extend: {},
  },
  plugins: [],
}