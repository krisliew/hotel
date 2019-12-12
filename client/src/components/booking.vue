<template>
<section id="booking">
    <div class="header">
        <img src="../assets/img/headerStar.png"/>
        <h1>Booking</h1>
        <p>Make your reservation here.</p>
    </div>
    <div class="bodyBooking">
        <!-- <b-form @submit="onSubmit" @reset="onReset" v-if="show"> -->
        <b-form v-on:submit.prevent="onSubmit" @reset="onReset" v-if="show">
            
        <b-form-group
            id="input-group-1"
            label="Email address:"
            label-for="input-1"            
        >
            <b-form-input
            id="input-1"
            v-model="form.email"
            type="email"
            :state="validateEmail"
            placeholder="Enter email"            
            ></b-form-input>
            <b-form-invalid-feedback :state="validateEmail">
                Your email must be filled in correct format.
            </b-form-invalid-feedback>
            <b-form-valid-feedback :state="validateEmail">                
            </b-form-valid-feedback>
        </b-form-group>

        <b-form-group id="input-group-2" label=" Name:" label-for="input-2">
            <b-form-input
            id="input-2"
            v-model="form.name"
            :state="validateName"
            placeholder="Enter full name"            
            ></b-form-input>
            <b-form-invalid-feedback :state="validateName">
                Your name must be 1-50 characters long.
            </b-form-invalid-feedback>
            <b-form-valid-feedback :state="validateName">                
            </b-form-valid-feedback>
        </b-form-group>

        <b-form-group id="input-group-3" label=" Address:" label-for="input-3">
            <b-form-input
            id="input-3"
            v-model="form.address"
            :state="validateAddress"
            placeholder="Enter address"
            ></b-form-input>
            <b-form-invalid-feedback :state="validateAddress">
                Your address must be 1-150 characters long.
            </b-form-invalid-feedback>
            <b-form-valid-feedback :state="validateAddress">       
            </b-form-valid-feedback>
        </b-form-group>

        <b-form-group id="input-group-4" label=" Phone Number:" label-for="input-4">
            <b-form-input
            id="input-4"
            v-model="form.phoneNumber"
            type="tel"
            placeholder="Enter phone number"

            :state="validatePhoneNumber"
            ></b-form-input>
            <b-form-invalid-feedback :state="validatePhoneNumber">
                Your phone number must be filled in correct format.
            </b-form-invalid-feedback>
            <b-form-valid-feedback :state="validatePhoneNumber"> 
            </b-form-valid-feedback>
        </b-form-group>

        <b-form-group label="Gender:" id="input-group-5" :state="validateGender">
            <b-form-radio-group v-model="form.gender" id="radio-5" >
            <b-form-radio value="male">Male</b-form-radio>
            <b-form-radio value="female">Female</b-form-radio>
            </b-form-radio-group>
            <b-form-invalid-feedback :state="validateGender">
               You must select a gender.
            </b-form-invalid-feedback>
            <b-form-valid-feedback :state="validateGender"> 
            </b-form-valid-feedback>
        </b-form-group>


        <b-form-group id="input-group-6" label="Hotel room:" label-for="input-6" :state="validateRoom">
            <b-form-select
            id="input-6"
            v-model="form.room"
            :options="rooms"
            
            ></b-form-select>
            <b-form-invalid-feedback :state="validateRoom">
               You must select a room.
            </b-form-invalid-feedback>
            <b-form-valid-feedback :state="validateRoom"> 
            </b-form-valid-feedback>
        </b-form-group>

        <b-form-group id="input-group-7" label="Room view:" label-for="input-7" :state="validateRoomView">
            <b-form-select
            id="input-7"
            v-model="form.roomView"
            :options="roomViews"
            
            ></b-form-select>
            <b-form-invalid-feedback :state="validateRoomView">
               You must select a room view.
            </b-form-invalid-feedback>
            <b-form-valid-feedback :state="validateRoomView"> 
            </b-form-valid-feedback>            
        </b-form-group>
        
        <b-form-group id="input-group-8" label="Booking date:" label-for="input-8" :state="validateDatePicker">
            <HotelDatePicker 
                label="Booking Date"
                format="DD/MM/YYYY" 
                @check-in-changed="val => {form.checkIn = formatDates(val) }"
                @check-out-changed="val => {form.checkOut = formatDates(val)}"
            />
            <b-form-invalid-feedback :state="validateDatePicker">
               You must select a booking date.
            </b-form-invalid-feedback>
            <b-form-valid-feedback :state="validateDatePicker"> 
            </b-form-valid-feedback>
        </b-form-group>
        
        <b-form-group label="Special Request Note:" id="input-group-9">
            <b-form-textarea
                v-model="form.specialNote"
                id="textarea-auto-height"
                placeholder="Optional note"
                rows="3"
                max-rows="8"
            ></b-form-textarea>
        </b-form-group>

        <b-button type="submit" variant="primary">Book</b-button>
        <b-button type="reset" variant="danger">Reset</b-button>
        </b-form>
        
        <b-card class="mt-3" header="Form Data Result" v-show="testRes==false">
        <pre class="m-0">{{ form }}</pre>
        </b-card>
    </div>
</section>
</template>

<script>
import axios from 'axios'
import format from 'date-fns/format'
import HotelDatePicker from 'vue-hotel-datepicker'

export default {
  name: 'Booking',
  components:{
      HotelDatePicker
  },
  data () {
    return {       
        testRes:true,
        formClick: false,
        form: {
            email: '',
            name: '',
            address: '',
            phoneNumber : '',
            gender: '',
            room: null,
            roomView: null,
            checkIn: '',
            checkOut: '',
            specialNote:''
        },
        rooms: [{ text: 'Select One', value: null }, 'Queen 1', 'Queen 2', 'Two Room', 'Three Room','Bridal','Cottage Double','Cottage Triple','Cottage Four'],
        roomViews:[{ text: 'Select One', value: null }, 'Patio 1', 'Patio 2', 'Forest 1', 'Forest 2'],
        show: true      
    }
  },
    computed: {
      validateEmail(){
        const emailRe = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if(this.formClick){
            if(emailRe.test(this.form.email)){
                return true
            }else{
                return false
            }
        }
      },
      validateName() {
        if(this.formClick == true){
            return this.form.name.length > 0 && this.form.name.length < 26
        }
      },
      validateAddress(){
        if(this.formClick == true){
            return this.form.address.length > 0 && this.form.address.length < 151
        }
      },
      validatePhoneNumber(){
           const phoneRe = /^(\+?6?01)[0-46-9]-*[0-9]{7,8}$/
        if(this.formClick == true){
            if(this.form.phoneNumber.length > 0){
                if(phoneRe.test(this.form.phoneNumber)){
                    return true
                }else{
                    return false
                }
            }else{
                return false
            }
        }
      },
      validateGender(){
          if(this.formClick == true){
            return this.form.gender.length > 0
          }
      },
      validateRoom(){
        if(this.formClick == true){
            if(this.form.room == null){
                return false
            }else{
                return this.form.room.length > 0
            }
        }          
      },
      validateRoomView(){
        if(this.formClick == true){
            if(this.form.roomView == null){
                return false
            }else{
                return this.form.roomView.length > 0
            }
        }
      },
      validateDatePicker(){
        if(this.formClick == true){
            return this.form.checkIn.length > 0 && this.form.checkOut.length > 0
        }
      }
    },
    methods: {
      formatDates(date) {
        let formattedDates = ''
        if (date) {
            return format(date, 'YYYY/MM/DD')
        }
      },
      onSubmit(evt) {
          this.formClick = true
          console.log(this.form)
          
        if(this.validateName && this.validateAddress && this.validatePhoneNumber && this.validateEmail){
            console.log("Form passed!")

            axios.post('/api/postBooking',
                { 
                email:this.form.email,
                name:this.form.name,
                address:this.form.address,
                phoneNumber:this.form.phoneNumber,
                gender:this.form.gender,
                room:this.form.room,
                roomView:this.form.roomView,
                checkIn:this.form.checkIn,
                checkOut:this.form.checkOut,
                specialNote:this.form.specialNote           
                }).then(res => {
                    console.log(res) //Return of the Post method from localhost:5000/postBooking
                if(res.data.Error == 1){
                    //do smtg demo
                }              
                }).catch(err => {
                console.log(err)
                alert("Connection Error: Please try again later or contact support!")
                })        
        }
        
      },
      onReset(evt) {
        // Reset form values
        this.form.email = ''
        this.form.name = ''
        this.form.address = ''
        this.form.phoneNumber = ''
        this.form.gender = ''
        this.form.room = null
        this.form.roomView = null
        this.form.checkIn = ''
        this.form.checkOut = ''
        this.form.specialNote = ''
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.bodyBooking{
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
</style>
