<template>
  <b-container>
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-card>
          <b-card-header header-bg-variant="primary" header-text-variant="white">
            <h3>Listado de actas</h3>
          </b-card-header>
          <b-card-body>
            <TarjetaActaS v-for="acta in actas" :key="acta.id" :acta="acta" />
          </b-card-body>
          <b-card-footer align="right" footer-bg-variant="primary">
            <router-link :to="{ name: 'SecretarioCrearActa'}">
              <b-button variant="info">Crear nueva acta</b-button>
            </router-link>
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