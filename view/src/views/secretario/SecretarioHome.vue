<template>
  <div>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <TablaActasS :items="actas" />
      </b-col>
      <b-col>
        <b-col></b-col>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import ServiciosAPI from "@/services/ServiciosAPI.js";
import TablaActasS from "@/components/TablaActasS.vue";
export default {
  components: {
    TablaActasS
  },
  data() {
    return {
      actas: []
    };
  },
  created() {
    ServiciosAPI.getActasSecretario()
      .then(response => {
        this.actas = response.data;
      })
      .catch(error => {
        console.log("Ocurrió un error: " + error.response);
      });
  }
};
</script>

<style>
</style>