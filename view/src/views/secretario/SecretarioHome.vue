<template>
  <div>
    <b-row>
      <b-col></b-col>
      <b-col>
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
import TablasActasS from '@/components/TablasActasS.vue';
export default {
  components: {},
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