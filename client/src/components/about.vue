<template>
<section id="about">
<header class="text-white">
      <div class="container text-center">
        <h1>About the hotel</h1>                
      </div>
</header>
<div class="col-lg-8 mx-auto">
    <h1>About us</h1>
    <p>
        Giant Forest Inn is a hotel in South Pin Oak Drive Tracy.
        We offer not only a comfort living space, but also amenities 
        such as tennis courts, sauna baths, stables with a covered
        horseback trail, boat rentals, a ballroom, five dining rooms,
        and both an indoor and an out-door swimming pool since 1972.
    </p>
    <div class="btnDiv">
        <b-button pill class="btnBook" href="/room">Visit Luxury Room</b-button>
        <b-button pill class="btnBook" href="/cottage">Visit Cottage</b-button>
        
    </div>
</div>
</section>
</template>

<script>
export default {
    name:'About'
}
</script>

<style scoped>
.btnDiv{
    padding-top:30px;
    width:100%;
    text-align:center;
}
.btnBook{
    display: inline-block;
    background: #c1b086;
    color: #fff;
    border: 1px solid;
    border-color: #c1b086;
    border-radius: 1.875rem;
    font-size: 12px;
    font-size: .75rem;
    text-transform: uppercase;
    line-height: 1.2;
    padding: 1.5835em 3.333em;
    font-weight: 600;
    -webkit-transition: all .3s ease;
    transition: all .3s ease;
    text-align: center;
    letter-spacing: 1px;
    margin-left:20px;
    margin-right:20px;    
}
.btnBook:hover{
    background:darkblue;    
    transition: 0.3s;
}

p{
    text-indent: 20px;
    text-align: justify;
}
.col-lg-8 h1{
    text-align:center;
    padding-top:40px;
    padding-bottom:40px;
}
.col-lg-8{
    padding-bottom:40px;
}

header{
  padding: 186px 0 100px;
  height: 50vh;
  background: linear-gradient(rgba(0, 0, 0, 0.3), rgba(0, 0, 0, 0.3)), url('../assets/img/woodMonkey.jpg');
  background-size: cover;
  background-repeat:no-repeat;
}

</style>