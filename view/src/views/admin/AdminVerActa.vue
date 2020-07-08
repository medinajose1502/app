<template>
  <b-container>
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-card>
          <b-card-header header-bg-variant="primary" header-text-variant="white">
            <h3>Ver acta</h3>
          </b-card-header>
          <b-card-body>
            <b-row>
              <b-col>
                <label for="id">Número de la acta:</label>
                <b-form-input v-model="acta.id" id="id" readonly></b-form-input>
              </b-col>
              <b-col>
                <label for="tipo">Tipo de sesión:</label>
                <b-form-input v-model="acta.tipo" id="tipo" readonly></b-form-input>
              </b-col>
              <b-col>
                <label for="fecha">Fecha de la sesión:</label>
                <b-form-input v-model="acta.fecha" id="fecha" readonly></b-form-input>
              </b-col>
            </b-row>
            <p></p>
            <b-row>
              <label for="descripcion">Descripción de la acta:</label>
              <b-form-textarea v-model="acta.descripcion" id="descripcion" readonly></b-form-textarea>
            </b-row>
            <p></p>
            <b-row>
              <b-col cols="12">
                <a :href="enlace" download>
                  <b-button variant="success">
                    Descargar acta
                    <b-icon icon="arrow-down-square-fill"></b-icon>
                  </b-button>
                </a>
              </b-col>
            </b-row>
          </b-card-body>
          <b-card-footer footer-bg-variant="primary">
            <b-row>
              <b-col align="left">
                <router-link :to="{ name: 'AdminHome'}">
                  <b-button variant="info">
                    Volver a inicio
                    <b-icon icon="house-fill"></b-icon>
                  </b-button>
                </router-link>
              </b-col>
              <b-col align="right">
                <router-link :to="{ name: 'AdminEditarActa', params: {id: this.id} }">
                  <b-button type="submit" variant="info">
                    Editar
                    <b-icon icon="brush"></b-icon>
                  </b-button>
                </router-link>
              </b-col>
            </b-row>
          </b-card-footer>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import ServiciosAPI from "@/services/ServiciosAPI.js";
import { saveAs } from "file-saver";

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
    enlace: function() {
      return "http://localhost:8080/acta/descargar/" + this.acta.id;
    }
  }
};
</script>

<style>
</style>