<template>
  <div>
    <NavBarA />
    <b-container>
      <b-row>
        <b-col></b-col>
        <b-col cols="10">
          <b-card>
            <b-card-header header-bg-variant="primary" header-text-variant="white">
              <h4>Crear un decanato</h4>
            </b-card-header>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
              <b-row>
                <p></p>
                <b-card-body>
                  <b-row>
                    <b-col>
                      <b-form-input v-model="decanato.id" id="id" hidden readonly></b-form-input>
                      <b-form-group
                        id="grupo-nombre"
                        label="Nombre del decanato:"
                        label-for="nombre"
                        align="left"
                      >
                        <b-form-input
                          id="nombre"
                          v-model="decanato.nombre"
                          required
                          placeholder="Ingrese el nombre del decanato"
                        ></b-form-input>
                      </b-form-group>
                    </b-col>
                    <b-col>
                      <b-form-group
                        id="grupo-ubicacion"
                        label="Ubicación:"
                        label-for="ubicacion"
                        align="left"
                      >
                        <b-form-input
                          id="ubicacion"
                          v-model="decanato.ubicacion"
                          required
                          placeholder="Ingrese la ubicación del decanato."
                        ></b-form-input>
                      </b-form-group>
                    </b-col>
                  </b-row>

                  <p></p>
                  <b-row>
                    <b-col align="left">
                      <label for="descripcion">Descripcion:</label>
                      <b-form-textarea
                        id="descripcion"
                        v-model="decanato.descripcion"
                        placeholder="Ingrese una descripción para el decanato..."
                        rows="10"
                        max-rows="100"
                      ></b-form-textarea>
                    </b-col>
                  </b-row>

                  <p></p>
                </b-card-body>
              </b-row>
              <p></p>

              <b-card-footer footer-bg-variant="primary">
                <b-row align="left">
                  <b-col>
                    <router-link :to="{ name: 'AdminHome'}">
                      <b-button variant="info">
                        Volver a inicio
                        <b-icon icon="house-fill"></b-icon>
                      </b-button>
                    </router-link>
                  </b-col>
                  <b-col align="right">
                    <b-button type="reset" variant="danger">
                      Limpiar formulario
                      <b-icon icon="trash-fill"></b-icon>
                    </b-button>
                    <b-button type="submit" variant="success">
                      Editar
                      <b-icon icon="brush"></b-icon>
                    </b-button>
                  </b-col>
                </b-row>
              </b-card-footer>
            </b-form>
          </b-card>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
  </div>
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
        nombre: "",
        descripcion: "",
        ubicacion: ""
      },
      show: true
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
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      ServiciosAPI.guardarDecanato(this.decanato);
    },
    onReset(evt) {
      evt.preventDefault();
      // Reset our form values
      this.decanato.nombre = "";
      this.decanato.ubicacion = "";
      this.decanato.descripcion = "";
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    }
  }
};
</script>

<style>
</style>