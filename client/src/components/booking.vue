<template>
<section id="booking">
    <div class="header">
        <h1>Booking</h1>
        <p>Make your reservation here.</p>
    </div>
    <div class="bodyBooking">
        <b-form @submit="onSubmit" @reset="onReset" v-if="show">
        <b-form-group
            id="input-group-1"
            label="Email address:"
            label-for="input-1"
            description="We'll never share your email with anyone else."
        >
            <b-form-input
            id="input-1"
            v-model="form.email"
            type="email"
            required
            placeholder="Enter email"
            ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-2" label=" Name:" label-for="input-2">
            <b-form-input
            id="input-2"
            v-model="form.name"
            
            placeholder="Enter full name"
            ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-3" label=" Address:" label-for="input-3">
            <b-form-input
            id="input-3"
            v-model="form.address"
            
            placeholder="Enter address"
            ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-4" label=" Phone Number:" label-for="input-4">
            <b-form-input
            id="input-4"
            v-model="form.phoneNumber"
            type="tel"
            placeholder="Enter phone number"
            ></b-form-input>
        </b-form-group>

        <b-form-group label="Gender:" id="input-group-5">
            <b-form-radio-group v-model="form.gender" id="radio-5" >
            <b-form-radio value="Male">Male</b-form-radio>
            <b-form-radio value="Female">Female</b-form-radio>
            </b-form-radio-group>
        </b-form-group>


        <b-form-group id="input-group-6" label="Hotel room:" label-for="input-6">
            <b-form-select
            id="input-6"
            v-model="form.room"
            :options="rooms"
            
            ></b-form-select>
        </b-form-group>

        <b-form-group id="input-group-7" label="Room view:" label-for="input-7">
            <b-form-select
            id="input-7"
            v-model="form.roomType"
            :options="roomTypes"
            
            ></b-form-select>
        </b-form-group>
        
        <b-form-group id="input-group-8" label="Date:" label-for="input-8">
            <HotelDatePicker 
                label="Booking Date"
                format="DD/MM/YYYY" 
                @check-in-changed="val => {form.checkIn = formatDates(val) }"
                @check-out-changed="val => {form.checkOut = formatDates(val)}"
            />
        </b-form-group>
        
        <b-form-group label="Special Request Note:" id="input-group-9">
            <b-form-textarea
                id="textarea-auto-height"
                placeholder="Optional note"
                rows="3"
                max-rows="8"
            ></b-form-textarea>
        </b-form-group>

        <b-button type="submit" variant="primary">Book</b-button>
        <b-button type="reset" variant="danger">Reset</b-button>
        </b-form>
        
        <b-card class="mt-3" header="Form Data Result">
        <pre class="m-0">{{ form }}</pre>
        </b-card>
    </div>
</section>
</template>

<script>
import format from 'date-fns/format'
import HotelDatePicker from 'vue-hotel-datepicker'

export default {
  name: 'Booking',
  components:{
      HotelDatePicker
  },
  data () {
    return {
        form: {
            email: '',
            name: '',
            address: '',
            phoneNumber : '',
            gender: '',
            room: null,
            roomType: null,
            checkIn: '',
            checkOut: ''    
        },
        rooms: [{ text: 'Select One', value: null }, 'Queen 1', 'Queen 2', 'Two Room', 'Three Room','Bridal','Cottage Double','Cottage Triple','Cottage Four'],
        roomTypes:[{ text: 'Select One', value: null }, 'Patio 1', 'Patio 2', 'Forest 1', 'Forest 2'],
        show: true      
    }
  },
    methods: {
      formatDates(date) {
        let formattedDates = ''
        if (date) {
            return format(date, 'DD/MM/YYYY')
        }
      },

      onSubmit(evt) {
        evt.preventDefault()
        console.log(JSON.stringify(this.form))
        alert(JSON.stringify(this.form))
      },
      onReset(evt) {
        evt.preventDefault()
        // Reset our form values
        this.form.email = ''
        this.form.name = ''
        this.form.address = ''
        this.form.phoneNumber = ''
        this.form.gender = ''
        this.form.room = null
        this.form.roomType = null
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
</style>
