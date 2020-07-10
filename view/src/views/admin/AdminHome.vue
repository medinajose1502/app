<template>
  <div>
    <NavBarA />
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
      <b-row>
        <b-col>
          <TablaEstatus :items="estatuses" />
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import ServiciosAPI from "@/services/ServiciosAPI.js";
import TablaDecanatos from "@/components/TablaDecanatos.vue";
import TablaActas from "@/components/TablaActas.vue";
import TablaUsuarios from "@/components/TablaUsuarios.vue";
import TablaEstatus from "@/components/TablaEstatus.vue";
export default {
  components: {
    TablaDecanatos,
    TablaActas,
    TablaUsuarios,
    TablaEstatus
  },
  data() {
    return {
      decanatos: [],
      actas: [],
      usuarios: [],
      estatuses: []
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

    ServiciosAPI.getEstatuses()
      .then(response => {
        this.estatuses = response.data;
      })
      .catch(error => {
        console.log("Ocurrió un error: " + error.response);
      });
  }
};
</script>

<style>
</style>