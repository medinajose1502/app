<template>
  <b-row>
    <b-col cols="4">
      <b-card>
        <b-card-header header-bg-variant="primary" header-text-variant="white">
          <h3>Listado de decanatos</h3>
        </b-card-header>
        <TarjetaDecanato v-for="decanato in decanatos" :key="decanato.id" :decanato="decanato" />
        <b-card-footer align="right" footer-bg-variant="primary">
          <router-link :to="{ name: 'AdminCrearDecanato'}">
            <b-button variant="info">Crear decanato</b-button>
          </router-link>
        </b-card-footer>
      </b-card>
    </b-col>
    <b-col cols="5">
      <b-card>
        <b-card-header header-bg-variant="primary" header-text-variant="white">
          <h3>Listado de actas</h3>
        </b-card-header>
        <TarjetaActaA v-for="acta in actas" :key="acta.id" :acta="acta" />
        <b-card-footer align="right" footer-bg-variant="primary">
          <router-link :to="{ name: 'AdminCrearActa'}">
            <b-button variant="info">Crear acta</b-button>
          </router-link>
        </b-card-footer>
      </b-card>
    </b-col>
    <b-col>
      <b-card>
        <b-card-header header-bg-variant="primary" header-text-variant="white">
          <h3>Listado de usuarios</h3>
        </b-card-header>
        <TarjetaUsuario v-for="usuario in usuarios" :key="usuario.id" :usuario="usuario" />
        <b-card-footer align="right" footer-bg-variant="primary">
          <router-link :to="{ name: 'AdminCrearUsuario'}">
            <b-button variant="info">Crear usuario</b-button>
          </router-link>
        </b-card-footer>
      </b-card>
    </b-col>
  </b-row>
</template>

<script>
import ServiciosAPI from "@/services/ServiciosAPI.js";
import TarjetaActaA from "@/components/TarjetaActaA.vue";
import TarjetaDecanato from "@/components/TarjetaDecanato.vue";
import TarjetaUsuario from "@/components/TarjetaUsuario.vue";
export default {
  components: {
    TarjetaActaA,
    TarjetaDecanato,
    TarjetaUsuario
  },
  data() {
    return {
      decanatos: [],
      actas: [],
      usuarios: []
    };
  },
  created() {
    ServiciosAPI.getDecanatos()
      .then(response => {
        this.decanatos = response.data;
      })
      .catch(error => {
        console.log("Ocurrió un error: " + error.response);
      });

    ServiciosAPI.getActas()
      .then(response => {
        this.actas = response.data;
      })
      .catch(error => {
        console.log("Ocurrió un error: " + error.response);
      });

    ServiciosAPI.getUsuarios()
      .then(response => {
        this.usuarios = response.data;
      })
      .catch(error => {
        console.log("Ocurrió un error: " + error.response);
      });
  }
};
</script>

<style>
</style>