<template>
  <b-container>
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-card>
          <b-card-header header-bg-variant="primary" header-text-variant="white">
            <h3>Editar acta</h3>
          </b-card-header>
          <b-form @submit="onSubmit" @reset="onReset" v-if="show">
            <b-row>
              <b-col md="auto">
                <p></p>
                <b-form-input v-model="acta.id" id="id" hidden readonly></b-form-input>
                <b-form-select id="tipo" v-model="acta.tipo" class="mb-3">
                  <b-form-select-option value="O">Ordinaria</b-form-select-option>
                  <b-form-select-option value="E">Extraordinaria</b-form-select-option>
                </b-form-select>
                <p></p>
                <b-calendar
                  v-model="acta.fecha"
                  v-bind="labels"
                  :start-weekday="weekday"
                  :hide-header="hideHeader"
                  :show-decade-nav="showDecadeNav"
                ></b-calendar>
              </b-col>
              <b-col>
                <b-form-textarea
                  id="textarea"
                  v-model="acta.descripcion"
                  placeholder="Ingrese los detalles de la sesión a registrar..."
                  rows="17"
                  max-rows="10000"
                ></b-form-textarea>
              </b-col>
            </b-row>
            <p></p>
            <b-card-footer align="right" footer-bg-variant="primary">
              <b-button variant="warning">Volver</b-button>
              <b-button type="reset" variant="danger">Eliminar</b-button>
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
    id: {
      type: Number
    }
  },
  data() {
    return {
      acta: {
        id: "",
        tipo: "O",
        fecha: "",
        descripcion: ""
      },
      show: true,
      showDecadeNav: false,
      hideHeader: false,
      weekday: 0,
      weekdays: [
        { value: 0, text: "Domingo" },
        { value: 1, text: "Lunes" },
        { value: 2, text: "Martes" },
        { value: 3, text: "Miércoles" },
        { value: 4, text: "Jueves" },
        { value: 5, text: "Viernes" },
        { value: 6, text: "Sábado" }
      ],
      labels: {
        labelPrevDecade: "Década pasda",
        labelPrevYear: "Año pasado",
        labelPrevMonth: "Mes pasado",
        labelCurrentMonth: "Mes actual",
        labelNextMonth: "Próximo mes",
        labelNextYear: "Próximo año",
        labelNextDecade: "Próxima década",
        labelToday: "Hoy",
        labelSelected: "Fecha seleccionada",
        labelNoDateSelected: "No ha seleccinado una fecha",
        labelCalendar: "Calendario",
        labelNav: "Navegación",
        labelHelp: "Use las flechas para selecciona una fecha"
      }
    };
  },
  created() {
    this.acta.id = this.id;
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      ServiciosAPI.guardarActa(this.acta);
    },
    onReset(evt) {
      evt.preventDefault();
      // Reset our form values
      this.acta.tipo = "O";
      this.acta.fecha = "";
      this.acta.descripcion = "";
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