<template>
  <b-container>
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-card>
          <b-card-header header-bg-variant="primary" header-text-variant="white">
            <h3>Ver acta</h3>
          </b-card-header>
          <b-form text-variant="black" align="left">
            <p></p>
            <label for="id">Número del acta</label>
            <b-form-input v-model="acta.id" id="id" readonly></b-form-input>
            <p></p>
            <label for="tipo">Tipo de sesión</label>
            <b-form-input v-model="tipoSesion" id="tipo" readonly></b-form-input>
            <p></p>
            <label for="fecha">Fecha de la sesión</label>
            <b-form-input v-model="acta.fecha" id="fecha" readonly></b-form-input>
            <p></p>
            <label for="descripcion">Descripción del acta</label>
            <b-form-textarea v-model="acta.descripcion" id="descripcion" readonly></b-form-textarea>
            <p></p>
            <b-card-footer align="right" footer-bg-variant="primary">
              <router-link :to="{ name: 'SecretarioHome'}">
                <b-button variant="danger">Volver</b-button>
              </router-link>
              <router-link :to="{ name: 'SecretarioEditarActa', params: { id: this.id }}">
                <b-button variant="info">Editar</b-button>
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
      acta: {
        id: "",
        tipo: "",
        fecha: "",
        descripcion: ""
      }
    };
  },
  created() {
    ServiciosAPI.getActa(this.id)
      .then(response => {
        this.acta = response.data;
      })
      .catch(error => {
        console.log("Ocurrió un error: " + error.response);
      });
  },
  computed: {
    tipoSesion: function() {
      if (this.acta.tipo == "E") return "Extraordinaria";
      else return "Ordinaria";
    }
  }
};
</script>

<style>
</style>