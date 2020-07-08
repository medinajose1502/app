<template>
  <b-container>
    <b-row>
      <b-col>
        <b-card>
          <b-card-header header-bg-variant="primary" header-text-variant="white">
            <h3>Editar usuario</h3>
          </b-card-header>
          <b-card-body>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
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
                  type="password"
                  required
                  placeholder="Ingrese la contraseña del usuario."
                ></b-form-input>
              </b-form-group>

              <b-form-group id="input-group-3" label="Nombres" label-for="input-3" align="left">
                <b-form-input
                  id="input-3"
                  v-model="usuario.nombres"
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
              <b-card-footer align="right" footer-bg-variant="primary">
                <b-button variant="warning">Volver</b-button>
                <b-button type="reset" variant="danger">Eliminar</b-button>
                <b-button type="submit" variant="info">Actualizar</b-button>
              </b-card-footer>
              <p></p>
            </b-form>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import ServiciosAPI from "@/services/ServiciosAPI.js";
export default {
  props: ["id"],
  data() {
    return {
      decanatos: [],
      usuario: {
        id: null,
        cedula: "",
        contrasenna: "",
        nombres: "",
        apellidos: "",
        roles: [],
        decanato: {
          id: ""
        }
      },
      show: true,
      opc: []
    };
  },
  async created() {
    var dec = await ServiciosAPI.getDecanatos();
    if (dec.status == 200) {
      this.decanatos = dec.data;
      this.crearOpciones();
    }

    var usu = await ServiciosAPI.getUsuarioId(this.id);
    if (usu.status == 200) this.usuario = usu.data;
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      this.usuario.roles.push(this.rol);
      ServiciosAPI.guardarUsuario(this.usuario);
    },
    onReset(evt) {
      evt.preventDefault();
      // Reset our form values
      this.usuario.cedula = "";
      this.usuario.contrasenna = "";
      this.usuario.nombres = "";
      this.usuario.apellidos = "";
      this.usuario.roles = [];
      this.usuario.decanato.id = "";
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
    crearOpciones() {
      this.decanatos.forEach(decanato => {
        var op = { value: decanato.id, text: decanato.nombre };
        this.opc.push(op);
      });
    }
  }
};
</script>

<style>
</style>