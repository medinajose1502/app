<template>
  <div>
    <NavBarA />
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

              <b-card-footer footer-bg-variant="primary">
                <b-row>
                  <b-col>
                    <router-link :to="{ name: 'AdminHome'}">
                      <b-button align="left" variant="info">
                        Volver a inicio
                        <b-icon icon="house-fill"></b-icon>
                      </b-button>
                    </router-link>
                  </b-col>
                  <b-col align="right">
                    <router-link :to="{ name: 'AdminEditarDecanato', params: {id: this.id} }">
                      <b-button type="submit" variant="info">
                        Editar
                        <b-icon icon="brush"></b-icon>
                      </b-button>
                    </router-link>
                  </b-col>
                </b-row>
              </b-card-footer>
            </b-form>
          </b-card>
        </b-col>
        <b-col></b-col>
      </b-row>
      <p></p>
      <p></p>
      <b-row>
        <b-col></b-col>
        <b-col cols="10">
          <TablaActas :items="decanato.actas" />
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import ServiciosAPI from "@/services/ServiciosAPI.js";
import TablaActas from "@/components/TablaActas.vue";

export default {
  components: {
    TablaActas
  },
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
        descripcion: "",
        actas: []
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