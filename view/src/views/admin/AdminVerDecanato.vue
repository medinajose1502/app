<template>
  <b-container>
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-card>
          <b-card-header header-bg-variant="primary" header-text-variant="white">
            <h3>Ver decanato</h3>
          </b-card-header>
          <b-form text-variant="black" align="left">
            <b-card-body>
              <p></p>
              <label for="nombres">Nombre del decanato:</label>
              <b-form-input v-model="decanato.nombre" id="nombre" readonly></b-form-input>
              <p></p>

              <label for="descripcion">Descripción del decanato:</label>
              <b-form-textarea v-model="decanato.descripcion" id="descripcion" readonly></b-form-textarea>
              <p></p>

              <label for="ubicacion">Ubicación:</label>
              <b-form-input v-model="decanato.ubicacion" id="ubicacion" readonly></b-form-input>
              <p></p>
            </b-card-body>

            <b-card-footer align="right" footer-bg-variant="primary">
              <router-link :to="{ name: 'AdminHome'}">
                <b-button variant="danger">Volver</b-button>
              </router-link>
              <router-link :to="{ name: 'AdminEditarDecanato', params: {id: this.id} }">
                <b-button type="submit" variant="info">Editar</b-button>
              </router-link>
            </b-card-footer>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import ServiciosAPI from "@/services/ServiciosAPI.js";

export default {
  props: {
    id: {
      type: Number
    }
  },
  data() {
    return {
      decanato: {
        id: "",
        tipo: "",
        fecha: "",
        descripcion: ""
      }
    };
  },
  created() {
    ServiciosAPI.getDecanato(this.id)
      .then(response => {
        this.decanato = response.data;
      })
      .catch(error => {
        console.log("Ocurrió un error: " + error.response);
      });
  }
};
</script>

<style>
</style>