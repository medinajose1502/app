<template>
  <b-container>
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-card>
          <b-card-header header-bg-variant="primary" header-text-variant="white">
            <h3>Crear usuario</h3>
          </b-card-header>
          <b-form @submit="onSubmit" @reset="onReset" v-if="show">
            <b-row>
              <p></p>

              <b-form-group id="input-group-1" label="Cédula" label-for="input-1" align="left">
                <b-form-input
                  id="input-1"
                  v-model="usuario.cedula"
                  required
                  placeholder="Ingrese la cédula del usuario."
                ></b-form-input>
              </b-form-group>

              <b-form-group id="input-group-2" label="Contraseña" label-for="input-2" align="left">
                <b-form-input
                  id="input-2"
                  v-model="usuario.contrasenna"
                  required
                  placeholder="Ingrese la cédula del usuario."
                ></b-form-input>
              </b-form-group>

              <b-form-group id="input-group-3" label="Nombres" label-for="input-3" align="left">
                <b-form-input
                  id="input-3"
                  v-model="usuario.nombre"
                  required
                  placeholder="Ingrese los nombres del usuario."
                ></b-form-input>
              </b-form-group>

              <b-form-group id="input-group-4" label="Apellidos" label-for="input-4" align="left">
                <b-form-input
                  id="input-4"
                  v-model="usuario.apellidos"
                  required
                  placeholder="Ingrese los apellidos del usuario."
                ></b-form-input>
              </b-form-group>

              <p></p>

              <b-form-select id="tipo" v-model="decanato" :options="opciones" class="mb-3"></b-form-select>

              <b-form-select id="tipo" v-model="rol.tipo" class="mb-3">
                <b-form-select-option value="ROLE_ADMIN">Admin</b-form-select-option>
                <b-form-select-option value="ROLE_SECRETARIO">Secretario</b-form-select-option>
              </b-form-select>
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
  props: {
    decanatos: {
      type: Array
    }
  },
  data() {
    return {
      opc: {
        value: "",
        text: ""
      },
      rol: {
        id: "",
        tipo: ""
      },
      usuario: {
        cedula: "",
        contrasenna: "",
        nombres: "",
        apellidos: "",
        roles: [rol],
        decanato: ""
      },
      show: true
    };
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      ServiciosAPI.guardarUsuario(this.acta);
    },
    onReset(evt) {
      evt.preventDefault();
      // Reset our form values
      this.usuario.cedula = "";
      this.usuario.contrasenna = "";
      this.usuario.nombres = "";
      this.usuario.apellidos = "";
      this.usuario.roles = [];
      this.usuario.decanato = "";
      this.opc.value = "";
      this.opc.text = "";
      this.rol.id = "";
      this.rol.tipo = "";
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    }
  },
  computed: {
    opciones: function() {
      temp = [];
      this.decanatos.forEach(decanato => {
        this.opc.value = decanato.id;
        this.opc.text = decanato.nombres;
        temp.push(this.opc);
      });
      return temp;
    }
  }
};
</script>

<style>
</style>