
import patientService from "../services/patientService";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import { DataGrid, GridActionsCellItem } from "@mui/x-data-grid";
import { useEffect } from "react";

const PatientTable = ({ patients,  onEditPatient, onDeletePatient }) => {
useEffect(() => {
    console.log("Patients data:", patients);
  }, [patients]);

  const columns = [
    { field: "firstName", headerName: "First Name", width: 120 },
    { field: "lastName", headerName: "Sure Name", width: 120 },
    { field: "address", headerName: "Address", width: 200, editable: false },
    { field: "city", headerName: "City", width: 150 },
    { field: "state", headerName: "State", width: 150 },
    { field: "zipCode", headerName: "Zip Code", width: 150 },
    { field: "phoneNumber", headerName: "Phone Number", width: 150 },
    { field: "email", headerName: "Email", width: 200 },

    {
      field: "actions",
      type: "actions",
      width: 100,
      getActions: (params) => [
        <GridActionsCellItem
          icon={<EditIcon />}
          label="Edit"
          onClick={() => onEditPatient(params.row)}
        />,
        <GridActionsCellItem
          icon={<DeleteIcon />}
          label="Delete"
          onClick={()=> onDeletePatient(params.row)}
          color="error"
          showInMenu
        />,
      ],
    },
  ];

  return (
    <DataGrid
      rows={patients}
      columns={columns}
      pageSizeOptions={[10,25,50]}
      checkboxSelection
      disableRowSelectionOnClick
      processRowUpdate={async (newRow)=>{
        try {
        await patientService.updatePatient(newRow.id,newRow);
        return newRow;

    } catch (err) {
          console.error("Error updating patient:", err);
          throw err;
        }
      }}
     getRowId={(row)=>row.id}
    />
  );
};

export default PatientTable;