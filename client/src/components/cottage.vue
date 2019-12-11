<template>
<section id="cottage">
<div class="header">
    <img src="../assets/img/headerStar.png"/>
    <h1> Cottages </h1>
    <p> The cottage is located in the wooded area near the Inn. These cottages provide seclusion for you and also a garage for your vehicle </p>
</div>
<div class="bodyList">
    <!-- Two Room Cottage -->
    <b-card no-body class="overflow-hidden">
        <b-row no-gutters>
            <b-col md="4">
                <b-card-img src="https://picsum.photos/id/236/2592/1944" class="rounded-0"></b-card-img>
            </b-col>
            <b-col class="roomDesc" md="8">
            <b-card-body title="Two Room Cottage">
                <b-card-text>
                <p>
                    Standard Single Rooms are designed in open-concept living area and have many facilities.
                </p>
                
                <table class="roomInfo">
                    <tr>
                        <td><v-icon name="male" class="listIcon"></v-icon></td>
                        <td>Adults: 2</td>
                    </tr>
                    <tr>
                        <td><v-icon name="expand" class="listIcon"></v-icon></td>
                        <td>Size: 200m²</td>
                    </tr>
                    <tr>
                        <td><v-icon name="bed" class="listIcon"></v-icon></td>
                        <td>Bed Type: queen bed</td>
                    </tr>
                    <tr>
                        <td><v-icon name="bookmark" class="listIcon"></v-icon></td>
                        <td>Catagories: single</td>
                    </tr>
                    <tr>
                        <td><v-icon name="star" class="listIcon"></v-icon></td>
                        <td>Amenities: air-conditioning, free wi-fi, hairdryer, in-room safety, laundry, minibar, telephone</td>
                    </tr>
                    <tr>
                        <td colspan="2" class="price"><span class="dollarSign">$</span>350++ <span class="perNight">/ per nights</span></td>
                    </tr>
                </table>
                <b-button pill class="btnBook" href="/booking">Book</b-button>
                </b-card-text>
            </b-card-body>
            </b-col>
        </b-row>
    </b-card>
    <!-- Three Room Cottage --> <br/>
    <b-card no-body class="overflow-hidden">
        <b-row no-gutters>
            <b-col md="4">
                <b-card-img src="https://picsum.photos/id/236/2592/1944" class="rounded-0"></b-card-img>
            </b-col>
            <b-col class="roomDesc" md="8">
            <b-card-body title="Three Room Cottage">
                <b-card-text>
                <p>
                    Superior Double Rooms are perfectly equipped for traveling couples or friends.
                </p>
                
                <table class="roomInfo">
                    <tr>
                        <td><v-icon name="male" class="listIcon"></v-icon></td>
                        <td>Adults: 2</td>
                    </tr>
                    <tr>
                        <td><v-icon name="expand" class="listIcon"></v-icon></td>
                        <td>Size: 300m²</td>
                    </tr>
                    <tr>
                        <td><v-icon name="bed" class="listIcon"></v-icon></td>
                        <td>Bed Type: queen bed</td>
                    </tr>
                    <tr>
                        <td><v-icon name="bookmark" class="listIcon"></v-icon></td>
                        <td>Catagories: double</td>
                    </tr>
                    <tr>
                        <td><v-icon name="star" class="listIcon"></v-icon></td>
                        <td>Amenities: air-conditioning, free wi-fi, hairdryer, in-room safety, laundry, minibar, telephone</td>
                    </tr>
                    <tr>
                        <td colspan="2" class="price"><span class="dollarSign">$</span>380++ <span class="perNight">/ per nights</span></td>
                    </tr>
                </table>
                <b-button pill class="btnBook" href="/booking">Book</b-button>
                </b-card-text>
            </b-card-body>
            </b-col>
        </b-row>
    </b-card>
    <!-- Four Room Cottage --> <br/>
    <b-card no-body class="overflow-hidden">
        <b-row no-gutters>
            <b-col md="4">
                <b-card-img src="https://picsum.photos/id/236/2592/1944" class="rounded-0"></b-card-img>
            </b-col>
            <b-col class="roomDesc" md="8">
            <b-card-body title="Four Room Cottage">
                <b-card-text>
                <p>
                    Perfectly suitable for family vacation or for big companies.
                </p>
                
                <table class="roomInfo">
                    <tr>
                        <td><v-icon name="male" class="listIcon"></v-icon></td>
                        <td>Adults: 2</td>
                    </tr>
                    <tr>
                        <td><v-icon name="child" class="listIcon"></v-icon></td>
                        <td>Children: 1</td>
                    </tr>
                    <tr>
                        <td><v-icon name="expand" class="listIcon"></v-icon></td>
                        <td>Size: 400m²</td>
                    </tr>
                    <tr>
                        <td><v-icon name="bed" class="listIcon"></v-icon></td>
                        <td>Bed Type: king bed, queen bed, single bed</td>
                    </tr>
                    <tr>
                        <td><v-icon name="bookmark" class="listIcon"></v-icon></td>
                        <td>Catagories: Four room</td>
                    </tr>
                    <tr>
                        <td><v-icon name="star" class="listIcon"></v-icon></td>
                        <td>Amenities: air-conditioning, free wi-fi, hairdryer, in-room safety, laundry, minibar, telephone</td>
                    </tr>
                    <tr>
                        <td colspan="2" class="price"><span class="dollarSign">$</span>400++ <span class="perNight">/ per nights</span></td>
                    </tr>
                </table>
                <b-button pill class="btnBook" href="/booking">Book</b-button>
                </b-card-text>
            </b-card-body>
            </b-col>
        </b-row>
    </b-card>

</div>

</section>
</template>

<script>
export default {
    name:'Cottage',
    data () {
        return {
            
        }
    }
}
</script>

<style scoped>
.bodyList{
    width:80%;
    margin-left: auto;
    margin-right: auto; 
    padding-bottom: 50px;
}
.header{
    padding-top:50px;
    padding-bottom:18px;
    text-align: center;
}
.header h1{
    padding-top: 30px;    
    padding-bottom: 18px;
}
.card-text{
    color: #5f6060;
    font-family: "Open Sans",sans-serif;
}
.roomDesc{
    padding-left:30px;
}
.roomInfo{
    font-size: 14px;
    font-size: .875rem;
    color: #222;
    list-style: none;
    margin-bottom:40px;
}
.roomInfo td{
    padding-bottom:8px;
}
.listIcon{
    color:#c1b086;
    margin-right:12px;
}
.price{
    font-size: 30px;
    font-size: 1.875rem;
    color: #222;
    padding-top:20px;
}
.dollarSign{
    vertical-align: text-top;
    font-size: 55%;
}
.perNight{
    color: #5f6060;
    font-size: 50%;
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
}
.btnBook:hover{
    background:darkblue;    
    transition: 0.3s;
}
</style>