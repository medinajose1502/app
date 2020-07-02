<template>
  <b-container>
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-card
          border-variant="primary"
          header-bg-variant="primary"
          header="Crear una acta"
          text-variant="white"
          footer-bg-variant="primary"
        >
          <b-form @submit="onSubmit" @reset="onReset" v-if="show">
            <b-row>
              <b-col md="auto">
                <b-form-select id="tipo" v-model="acta.tipo" class="mb-3">
                  <b-form-select-option value="O">Ordinaria</b-form-select-option>
                  <b-form-select-option value="E">Extraordinaria</b-form-select-option>
                </b-form-select>
                <p></p>
                <b-calendar
                  v-model="acta.fecha"
                  @context="onContext"
                  :locale="es"
                  v-bind="labels[locale]"
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
            <b-card-footer align="right">
              <b-button variant="danger">Limpiar</b-button>
              <b-button variant="primary">Enviar</b-button>
            </b-card-footer>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  data() {
    return {
      acta: {
        tipo: "O",
        fecha: "",
        descripcion: ""
      },
      showDecadeNav: false,
      hideHeader: false,
      locale: "es",
      locales: [{ value: "es", text: "Español (es-VE)" }],
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
        es: {
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
      }
    };
  },
  methods: {}
};
</script>

<style>
</style>