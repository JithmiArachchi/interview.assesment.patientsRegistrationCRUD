import { useState } from "react";
import { usePatients } from "../hooks/usePatients";
import PatientTable from "../components/PatientTable";
import LoadingSpinner from "../components/LoadingSpinner";
import ConfirmationDialog from "../components/ConfirmationDialog";
import PatientForm from "../components/PatientForm";
import patientService from "../services/patientService";

const Dashboard = () => {
  const { patients, loading, error, refetch } = usePatients();
  const [openForm, setOpenForm] = useState(false);
  const [selectedPatient, setSelectedPatient] = useState(null);
  const [deleteDialogOpen, setDeleteDialogOpen] = useState(false);
  const [patientToDelete, setPatientToDelete] = useState(null);

  const handleAddNewPatient = () => {
    setSelectedPatient(null);
    setOpenForm(true);
  };

  const handleEditPatient = (patient) => {
    setSelectedPatient(patient);
    setOpenForm(true);
  };

  const handleDeletePatient = (patient) => {
    setPatientToDelete(patient);
    setDeleteDialogOpen(true);
  };
    const confirmDeletePatient = async () => {
        try {
            await patientService.deletePatient(patientToDelete.id);
            setDeleteDialogOpen(false);
            setPatientToDelete(null);
            refetch();
        } catch (err) {
            console.error("Error deleting patient:", err);
        }
    };

  if (loading) return <LoadingSpinner />;
  if (error) return <div>Error: {error}</div>;
    return (
    <div className="min-h-screen bg-gray-100 p-4">
        <div className="max-w-7xl mx-auto">
      <div className="flex justify-between items-center mb-4">
        <h1 className="text-2xl font-bold text-green-600" >Patient Dashboard</h1>
        <button onClick={handleAddNewPatient} className="bg-blue-600 rounded-2xl p-2">
             Add New Patient</button>
      </div>
      </div>
      <PatientTable
        patients={patients}
        onEditPatient={handleEditPatient}
        onDeletePatient={handleDeletePatient}/>

        <PatientForm 
        open={openForm}
        onClose={() => setOpenForm(false)}
        patient={selectedPatient}
        patients={patients}
        onSuccess={() => {
          setOpenForm(false);
          refetch();
        }} />

        <ConfirmationDialog
        open={deleteDialogOpen}
        title="Delete Patient"
        message="Are you sure you want to delete this patient?"
        onConfirm={confirmDeletePatient}
        onCancel={() => setDeleteDialogOpen(false)} />

        </div>
    );
};

export default Dashboard;

