<template>
  <b-row>
    <b-col cols="4">
      <b-card>
        <b-card-header header-bg-variant="primary" header-text-variant="white">
          <h3>Listado de decanatos</h3>
        </b-card-header>
        <TarjetaDecanato v-for="decanato in decanatos" :key="decanato.id" :decanato="decanato" />
        <b-card-footer align="right" footer-bg-variant="primary">
          <b-button variant="info">Crear decanato</b-button>
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
          <b-button variant="info">Crear acta</b-button>
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
          <b-button variant="info">Crear usuario</b-button>
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
  },
  computed: {
    extraerActas: function() {
      this.decanatos.forEach(decanato => {
        decanato.actas.forEach(acta => {
          this.actas.push(acta);
        });
      });
    },

    extraerUsuarios: function() {
      this.decanatos.forEach(decanato => {
        decanato.usuarios.forEach(usuario => {
          this.usuarios.push(usuario);
        });
      });
    }
  }
};
</script>

<style>
</style>