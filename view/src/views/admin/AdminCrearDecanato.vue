<template>
  <b-container>
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-card>
          <b-card-header header-bg-variant="primary" header-text-variant="white">
            <h3>Crear decanato</h3>
          </b-card-header>
          <b-form @submit="onSubmit" @reset="onReset" v-if="show">
            <b-row>
              <p></p>
              <b-card-body>
                <b-form-group id="input-group-1" label="Cédula" label-for="input-1" align="left">
                  <b-form-input
                    id="input-1"
                    v-model="decanato.nombre"
                    required
                    placeholder="Ingrese el nombre del decanato"
                  ></b-form-input>
                </b-form-group>

                <p></p>

                <label for="descripcion"></label>
                <b-form-textarea
                  id="descripcion"
                  v-model="decanato.descripcion"
                  placeholder="Ingrese una descripción para el decanato..."
                  rows="17"
                  max-rows="100"
                ></b-form-textarea>

                <p></p>

                <b-form-group
                  id="input-group-2"
                  label="Contraseña"
                  label-for="input-2"
                  align="left"
                >
                  <b-form-input
                    id="input-2"
                    v-model="decanato.ubicacion"
                    required
                    placeholder="Ingrese la ubicación del decanato."
                  ></b-form-input>
                </b-form-group>
              </b-card-body>
            </b-row>
            <p></p>

            <b-card-footer align="right" footer-bg-variant="primary">
              <b-button variant="warning">Volver</b-button>
              <b-button type="reset" variant="danger">Limpiar</b-button>
              <b-button type="submit" variant="info">Enviar</b-button>
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
  data() {
    return {
      decanato: {
        nombre: "",
        descripcion: "",
        ubicacion: ""
      },
      show: true
    };
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      ServiciosAPI.guardarDecanato(this.decanato);
    },
    onReset(evt) {
      evt.preventDefault();
      // Reset our form values
      this.decanato.nombre = "O";
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