<template>
  <b-container>
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-card>
          <b-card-header header-bg-variant="primary" header-text-variant="white">
            <h3>Ver usuario</h3>
          </b-card-header>

          <b-form text-variant="black" align="left">
            <b-card-body>
              <p></p>
              <label for="cedula">Cédula:</label>
              <b-form-input v-model="usuario.cedula" id="cedula" readonly></b-form-input>
              <p></p>
              <label for="nombres">Nombres:</label>
              <b-form-input v-model="usuario.nombres" id="nombres" readonly></b-form-input>
              <p></p>
              <label for="apellidos">Apellidos</label>
              <b-form-input v-model="usuario.apellidos" id="apellidos" readonly></b-form-input>
              <p></p>
              <label for="rol">Rol del usuario</label>
              <b-form-textarea v-model="usuario.roles[0].tipo" id="rol" readonly></b-form-textarea>
              <p></p>
            </b-card-body>
            <b-card-footer align="right" footer-bg-variant="primary">
              <router-link :to="{ name: 'AdminHome'}">
                <b-button variant="danger">Volver</b-button>
              </router-link>
              <router-link :to="{ name: 'AdminEditarUsuario', params: {cedula: this.cedula} }">
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
    cedula: {
      type: Number
    }
  },
  data() {
    return {
      usuario: {
        cedula: "",
        nombres: "",
        apellidos: "",
        roles: [],
        actas: []
      }
    };
  },
  created() {
    ServiciosAPI.getUsuario(this.cedula)
      .then(response => {
        this.usuario = response.data;
      })
      .catch(error => {
        console.log("Ocurrió un error: " + error.response);
      });
  }
};
</script>

<style>
</style>