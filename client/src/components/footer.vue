<template>
<footer id="footer">
    <div class="container">
        
      <div class="footer-block">      
          <img src="..\assets\img\Giant forest Inn Hotel Logo.png"/>    
          <p class="footerInfo">
          Address: 7921 South Pin Oak Drive Tracy, CA 95376 <br />
          Contact-No: 209-200-6070 <br />
          Email: <a href="mailto:stackoverkill@outlook.com?Subject=Enquiry%20on%20Giant%20Forest%20Inn"> giantForestInn@hotel.com</a>
          </p>
      </div>      
    </div>
</footer>
</template>

<script>
export default {
    name:'Footer'
}
</script>

<style scoped>
div img{
    float:left; 
    width:500px;   
    padding-right: 150px;
}
#footer {    
    margin-left:auto;
    margin-right:auto;
    width: 100%;
    position: absolute;    
    background-color: #f5f5f5;
    padding-top:20px;
    padding-bottom:40px;
}
.footer-block {
    margin: 20px 0;
}
.footerInfo{
}

</style>