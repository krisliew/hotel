<template>
<nav class="navbar navbar-expand-md bg-light navbar-light">
  <a class="navbar-brand" href="/">Giant Forest Inn</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="/about">About</a>
      </li>
      <b-nav-item-dropdown text="Rooms & Cottages" right>
        <b-dropdown-item href="/room">Room</b-dropdown-item>
        <b-dropdown-item href="/cottage">Cottages</b-dropdown-item>
      </b-nav-item-dropdown>
      <li class="nav-item">
          <a class="nav-link" href="/booking">Booking</a>
      </li>    
      <li class="nav-item">
        <a class="nav-link" href="/contact">Contact</a>
      </li>    
    </ul>
  </div>  
</nav>
</template>

<script>
export default {
    name:'Navigation',
  data(){ return{} },
  
}
</script>

<style scoped>
</style>