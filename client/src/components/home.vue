<template>
<section id="home">
    <header class="text-white">
      <div class="container text-center">
        <h1>Giant Forest Inn</h1>
        <p class="lead"><b>Luxury and Private </b></p>        
        <br /> <br />
        <a class="btn btn-primary waves-effect border-0 btn-lg" href="/booking">Book now</a>        
        <a class="btn btn-white waves-effect btn-lg border-white text-white" href="/room">Read more</a>
      </div>
    </header>
</section>
</template>

<script>
export default {
  name: 'Home',
  data () {
    return {
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.container a{
  margin-left:20px;
  margin-right:20px;
}
#home{
  padding: 156px 0 100px;
  height: 100vh;
  background: linear-gradient(rgba(0, 0, 0, 0.3), rgba(0, 0, 0, 0.3)), url('../assets/img/mossyforest.jpg');
  /* background-image: url(../assets/img/mossyforest.jpg); */
  background-size: cover;
  background-repeat:no-repeat;
}
</style>
