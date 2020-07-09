<template>
  <div>
    <NavBarA />
    <b-container>
      <b-row>
        <b-col></b-col>
        <b-col cols="10">
          <b-card class="shadow-soft">
            <b-card-header header-bg-variant="primary shadow-inset">
              <h3>Crear acta</h3>
            </b-card-header>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
              <b-card-body>
                <b-row>
                  <p></p>
                  <b-col md="auto">
                    <b-form-select required id="tipo" v-model="acta.tipo" class="mb-3">
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
                      rows="16"
                      required
                      max-rows="10000"
                    ></b-form-textarea>
                  </b-col>
                </b-row>
                <p></p>
                <b-row>
                  <b-form-file
                    v-model="acta.archivo"
                    :state="Boolean(acta.archivo)"
                    placeholder="¡Elige un archivo o arrastralo hasta aquí!"
                    drop-placeholder="¡Sueltalo aquí!"
                    accept=".pdf"
                    size="lg"
                    browse-text="Buscar"
                  ></b-form-file>
                </b-row>
                <p></p>
              </b-card-body>
              <b-card-footer footer-bg-variant="primary">
                <b-row align="left">
                  <b-col>
                    <router-link :to="{ name: 'AdminHome'}">
                      <b-button variant="primary text-info">
                        Volver a inicio
                        <b-icon icon="house-fill"></b-icon>
                      </b-button>
                    </router-link>
                  </b-col>
                  <b-col align="right">
                    <b-button type="reset" variant="primary text-danger">
                      Limpiar formulario
                      <b-icon icon="trash-fill"></b-icon>
                    </b-button>
                    <b-button type="submit" variant="primary text-success">
                      Crear
                      <b-icon icon="plus-square-fill"></b-icon>
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
  data() {
    return {
      acta: {
        tipo: "O",
        fecha: "",
        descripcion: "",
        archivo: null
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
  methods: {
    async onSubmit(evt) {
      evt.preventDefault();
      ServiciosAPI.guardarActa(this.acta);
    },
    onReset(evt) {
      evt.preventDefault();
      // Reset our form values
      this.acta.tipo = "O";
      this.acta.fecha = "";
      this.acta.descripcion = "";
      this.acta.archivo = null;
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
.b-calendar .b-calendar-grid-body .col[data-date] .btn {
  height: auto !important;
}
</style>