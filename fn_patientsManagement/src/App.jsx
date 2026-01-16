import { useState } from "react";
import Dashboard from "./pages/Dashboard";
import "./App.css";

function App() {
  return (
   <>
    <div className="App">
      <Dashboard />
    </div>
    <footer className="text-center p-4 bg-gray-200">
      &copy; {new Date().getFullYear()} Patient Management System
    </footer>
   </>
  );
}

export default App;