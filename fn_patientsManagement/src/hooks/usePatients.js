import { useState, useEffect } from "react";
import patientService from "../services/patientService";

export const usePatients = () => {
  const [patients, setPatients] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchPatients = async () => {
   
    try {
      setLoading(true);
      const data = await patientService.getAllPatients();
      setPatients(data);
    } catch (err) {
      setError("patients data null");
      console.error("Error fetching patients:", err);
    } finally {
      setLoading(false);
    }
  };
  useEffect(() => {
    fetchPatients();
  }, []);

  return { patients, loading, error, refetch: fetchPatients };
};
