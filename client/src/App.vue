<template>
  <div id="app">
    <Navigation />
    <router-view/>
    <Footer />
  </div>
</template>

<script>
import Navigation from './components/navigation'
import Footer from './components/footer'

export default {
  name: 'App',
  components:{
    'Navigation':Navigation,
    'Footer':Footer
  }
}
</script>

<style>
h1,h2,h3,h4{
    font-family: "Noto Serif",serif;
}

p{
  font-family: "Open Sans",sans-serif;
  font-size: 16px;    
  line-height: 1.75;    
}
</style>
