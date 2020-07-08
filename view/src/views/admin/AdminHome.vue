<template>
  <div>
    <b-row>
      <b-col>
        <TablaDecanatos :items="decanatos" />
      </b-col>
      <b-col>
        <TablaActas :items="actas" />
      </b-col>
      <b-col>
        <b-col>
          <TablaUsuarios :items="usuarios" />
        </b-col>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import ServiciosAPI from "@/services/ServiciosAPI.js";
import TarjetaActaA from "@/components/TarjetaActaA.vue";
import TarjetaDecanato from "@/components/TarjetaDecanato.vue";
import TarjetaUsuario from "@/components/TarjetaUsuario.vue";
import TablaDecanatos from "@/components/TablaDecanatos.vue";
import TablaActas from "@/components/TablaActas.vue";
import TablaUsuarios from "@/components/TablaUsuarios.vue";
export default {
  components: {
    TarjetaActaA,
    TarjetaDecanato,
    TarjetaUsuario,
    TablaDecanatos,
    TablaActas,
    TablaUsuarios
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