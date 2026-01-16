import { Add } from "@mui/icons-material";
import { useState, useEffect, use } from "react";

const PatientForm = ({ open, onClose, patient, patients, onSuccess }) => {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    address: "",
    city: "",
    state: "",
    zipCode: "",
    phoneNumber: [""],
    email: "",
    active: true,
  });

  useEffect(() => {
    if (patient) {
      setFormData({
        firstName: patient.firstName,
        lastName: patient.lastName,
        address: patient.address,
        city: patient.city,
        state: patient.state,
        zipCode: patient.zipCode,
        phoneNumber: patient.phoneNumber||[],
        email: patient.email,
        active: patient.active,
      });
    }
  }, [patient]);

  const handleTextChange = (e) => {
   const { name, value,type,checked } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: type ==="checkbox" ? checked: value,
    }));
  };


const handlePhoneChange = (index) => (e) => {
        const newPhones=[...formData.phoneNumber];
        newPhones[index] = e.target.value.trim();
        setFormData(prev => ({
          ...prev,
          phoneNumber: newPhones
        }));
  }

  const addPhoneField = () => {
    setFormData((prev)=>({
      ...prev,
      phoneNumber: [...prev.phoneNumber, ""],
    }));
  }

  const removePhoneField = (index) => {
    if (formData.phoneNumber.length === 1) return;  
    setFormData((prev) => ({
        ...prev ,phoneNumber: prev.phoneNumber.filter((_, i) => i !== index),
    }));
  }

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const url = patient
        ? `/api/patients/${patient.id}`
        : "/api/patients";
      const method = patient ? "PATCH" : "POST";

      const response = await fetch(url, {   
        method,
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });
      if (response.ok) {
        onSuccess();
        onClose();
        return;
      }else{
        let errorData
        try{
errorData= await response.json();
        }catch{
errorData={message:`HTTP ${response.status} `};
        }
        alert(`Failed to : ${patient?"Update":"Add" || 'Unknown error'}patient. ${errorData.message}`);
      }

    } catch (err) {
      console.error("Error submitting form:", err);
      alert("Failed to submit form. Please try again.");
    }

    if (response.ok) {
      onSuccess();
    }
  };
  if (!open) return null;

  return (
    <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center">
      <div className="bg-white p-6 rounded shadow-lg w-96">
        <h2 className="text-xl mb-4 text-green-600">
          {patient ? "Edit Patient" : "Add New Patient"}
        </h2>
        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block mb-1">First Name</label>
            <input
              type="text"
              name="firstName"
              value={formData.firstName}
              onChange={handleTextChange}
              className="w-full border border-gray-300 p-2 rounded"
              required
            />
          </div>
          <div>
            <label className="block mb-1">Last Name</label>
            <input
              type="text"
              name="lastName"
              value={formData.lastName}
              onChange={handleTextChange}
              className="w-full border border-gray-300 p-2 rounded"
              required
            />
          </div>
          <div>
            <label className="block mb-1">Status</label>
            <input
              type="checkbox"
              name="active"
              checked={formData.active}
              onChange={handleTextChange}              
              className="w-full border border-gray-300 p-2 rounded"              
            />
          </div>

            <div>
            <label className="block mb-1">Address</label>
            <input
              type="text"
              name="address"
              value={formData.address}
              onChange={handleTextChange}
              className="w-full border border-gray-300 p-2 rounded"
              required
            />
          </div>
          <div>
            <label className="block mb-1">City</label>
            <input
              type="text"
              name="city"
              value={formData.city}
              onChange={handleTextChange}
              className="w-full border border-gray-300 p-2 rounded"
              required
            />
          </div>
          <div>
            <label className="block mb-1">State</label>
            <input
              type="text"
              name="state"
              value={formData.state}
              onChange={handleTextChange}
              className="w-full border border-gray-300 p-2 rounded"
              required
            />
          </div>

          <div>
            <label className="block mb-1">Zip Code</label>
            <input
              type="text"
              name="zipCode"
              value={formData.zipCode}
              onChange={handleTextChange}
              className="w-full border border-gray-300 p-2 rounded"
              required
            />
          </div>
          <div>
            <label className="block mb-1">Phone Numbers</label>
            {formData.phoneNumber.map((phoneNumber, index) => (
              <div key={index} className="flex mb-2">
                <input
                  type="tel"
                  name="phoneNumber"
                  value={phoneNumber}
                  onChange={handlePhoneChange(index)}
                  className="w-full border border-gray-300 p-2 rounded"
                  required={index===0}
                />

               {formData.phoneNumber.length > 1 && (
                <button type="button" 
                onClick={() => removePhoneField(index)}
                 className="ml-2 px-3 py-1 bg-red-600 text-white rounded hover:bg-red-700">
                  Remove
                </button>
               )} 
               

              </div>
            ))}
<button type="button" onClick={addPhoneField}
                 className="ml-2 px-3 py-1 bg-blue-600 text-white rounded hover:bg-blue-700">
                 <Add/> Add Another
                </button>


          </div>
          <div>
            <label className="block mb-1">Email</label>
            <input
              type="email"
              name="email"
              value={formData.email}
              onChange={handleTextChange}
              className="w-full border border-gray-300 p-2 rounded"
              required
            />
          </div>
          <div className="flex justify-end space-x-2">
            <button
              type="button"
              onClick={onClose}
              className="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700"
            >
              {patient ? "Update" : "Add"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};
export default PatientForm;
