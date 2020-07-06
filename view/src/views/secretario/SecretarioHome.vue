<template>
  <b-container>
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-card>
          <b-card-header>
            <h3>Listado de actas</h3>
          </b-card-header>
          <b-card-text>
            <TarjetaActaS v-for="acta in actas" :key="acta.id" :acta="acta" />
          </b-card-text>
          <b-card-footer align="right">
            <b-button variant="primary">Crear una nueva acta</b-button>
          </b-card-footer>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import ServiciosAPI from "@/services/ServiciosAPI.js";
import TarjetaActaS from "@/components/TarjetaActaS.vue";
export default {
  components: {
    TarjetaActaS
  },
  data() {
    return {
      actas: []
    };
  },
  created() {
    ServiciosAPI.getActas()
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