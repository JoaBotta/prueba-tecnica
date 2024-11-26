import { Component, OnInit } from '@angular/core';
import { PlantaService } from '@core/services/planta.service';
import { Planta } from '@core/model/planta.model';
import Swal from 'sweetalert2';
import { CountryService } from '@core/services/country.service';

@Component({
  selector: 'app-planta',
  templateUrl: './planta.component.html',
  styleUrls: ['./planta.component.css'],
})
export class PlantaComponent implements OnInit {
  plantas: Planta[] = [];
  nuevaPlanta: Planta = {
    nombre: '',
    pais: '',
    lecturasOk: 0,
    alertasMedias: 0,
    alertasRojas: 0,
    sensoresDeshabilitados: 0,
  };

  lecturasOkTotal: number = 0;
  alertasMediasTotal: number = 0;
  alertasRojasTotal: number = 0;
  sensoresDeshabilitadosTotal: number = 0;

  listaPaises: any[] = []; 

  mostrarPrimerPopup: boolean = false;
  mostrarSegundoPopup: boolean = false;

  plantaEditando: Planta | any = null;
  mostrarPopupEdicion: boolean = false;


  constructor(
    private plantaService: PlantaService,
    private countryService: CountryService
  ) {}

  ngOnInit(): void {
    this.obtenerPlantas();
    this.obtenerTotales();
    this.cargarPaises(); 
  }

  cargarPaises(): void {
    this.countryService.getCountries().subscribe(
      (data) => {
        this.listaPaises = data.map((country) => ({
          name: country.name.common,
          flag: country.flags.svg,
        }));
        console.log('Paises cargados:', this.listaPaises);
      },
      (error) => console.error('Error al obtener países:', error)
    );
  }

  abrirPrimerPopup(): void {
    this.mostrarPrimerPopup = true;
  }

  irASegundoPopup(): void {
    if (this.nuevaPlanta.nombre?.trim() && this.nuevaPlanta.pais?.trim()) {
      this.mostrarPrimerPopup = false;
      this.mostrarSegundoPopup = true;
    } else {
      Swal.fire({
        icon: 'warning',
        title: 'Campos incompletos',
        text: 'Por favor, completa el nombre y el país de la planta.',
        confirmButtonText: 'Aceptar',
      });
    }
  }

  cerrarPopup(): void {
    this.mostrarPrimerPopup = false;
    this.mostrarSegundoPopup = false;
    this.mostrarPopupEdicion = false;
  }

  obtenerPlantas(): void {
    this.plantaService.getPlantas().subscribe((data) => {
      this.plantas = data;
    });
  }

  crearPlanta(): void {
    this.plantaService.createPlanta(this.nuevaPlanta).subscribe((planta) => {
      this.plantas.push(planta);
      this.nuevaPlanta = {
        nombre: '',
        pais: '',
        lecturasOk: 0,
        alertasMedias: 0,
        alertasRojas: 0,
        sensoresDeshabilitados: 0,
      };
      this.cerrarPopup();
      this.obtenerTotales();

      // Mostrar mensaje de éxito con Swal
      Swal.fire({
        icon: 'success',
        title: 'Creación exitosa',
        text: 'La planta ha sido creada correctamente.',
        confirmButtonText: 'Aceptar',
      });
    });
  }

  eliminarPlanta(id: number): void {
    Swal.fire({
      title: '¿Estás seguro?',
      text: '¿Quieres eliminar esta planta?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar',
    }).then((result) => {
      if (result.isConfirmed) {
        this.plantaService.deletePlanta(id).subscribe(() => {
          this.plantas = this.plantas.filter((planta) => planta.id !== id);
          this.obtenerTotales();

          // Mostrar mensaje de éxito con Swal
          Swal.fire({
            icon: 'success',
            title: 'Eliminación exitosa',
            text: 'La planta ha sido eliminada correctamente.',
            confirmButtonText: 'Aceptar',
          });
        });
      }
    });
  }

  obtenerTotales(): void {
    this.plantaService.getLecturasOk().subscribe((data) => {
      this.lecturasOkTotal = data;
    });

    this.plantaService.getAlertasMedias().subscribe((data) => {
      this.alertasMediasTotal = data;
    });

    this.plantaService.getAlertasRojas().subscribe((data) => {
      this.alertasRojasTotal = data;
    });
    this.plantaService.getSensoresDeshabilitados().subscribe((data) => {
      this.sensoresDeshabilitadosTotal = data;
    });
  }

  abrirPopupEdicion(planta: Planta): void {
    this.plantaEditando = { ...planta };
    this.mostrarPopupEdicion = true;

    
  }

  editarPlanta(): void {
    if (this.plantaEditando) {
      this.plantaService.updatePlanta(this.plantaEditando.id!, this.plantaEditando).subscribe(() => {
        this.obtenerPlantas();
        this.cerrarPopup();
        this.obtenerTotales();

        // Mostrar mensaje de éxito con Swal
        Swal.fire({
          icon: 'success',
          title: 'Edición exitosa',
          text: 'La planta ha sido editada correctamente.',
          confirmButtonText: 'Aceptar',
        });
      });
    }
  }

  // Bloquear la edición de 'nombre' y 'pais' después de la edición
  bloquearCamposEditar(): void {
    this.plantaEditando.nombre = this.plantaEditando.nombre; // Evitar edición
    this.plantaEditando.pais = this.plantaEditando.pais; // Evitar edición
  }
}