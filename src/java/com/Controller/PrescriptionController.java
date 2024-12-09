package com.Controller;

import com.DAO.PrescriptionDao;
import com.Model.Prescription;
import com.Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/PrescriptionController")
public class PrescriptionController extends HttpServlet {
    private PrescriptionDao prescriptionDao;

    public void init() {
        prescriptionDao = new PrescriptionDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "insert":
                    insertPrescription(request, response);
                    break;
                case "update":
                    if (isUserDoctor(request)) {
                        updatePrescription(request, response);
                    } else {
                        response.sendRedirect("AccessDenied.jsp");
                    }
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "delete":
                    if (isUserDoctor(request)) {
                        deletePrescription(request, response);
                    } else {
                        response.sendRedirect("AccessDenied.jsp");
                    }
                    break;
                case "edit":
                    if (isUserDoctor(request)) {
                        showEditForm(request, response);
                    } else {
                        response.sendRedirect("AccessDenied.jsp");
                    }
                    break;
                case "list":
                    listPrescription(request, response);
                    break;
                case "generate":
                    generatePrescription(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listPrescription(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Prescription> listPrescription = prescriptionDao.selectAllPrescriptions();
        request.setAttribute("listPrescription", listPrescription);
        request.getRequestDispatcher("ManagePrescription.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Prescription existingPrescription = prescriptionDao.selectPrescription(id);
        request.setAttribute("prescription", existingPrescription);
        request.getRequestDispatcher("EditPrescription.jsp").forward(request, response);
    }

    private void insertPrescription(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String patientName = request.getParameter("patientName");
        String doctorName = request.getParameter("doctorName");
        String medicineName = request.getParameter("medicineName");
        String dosage = request.getParameter("dosage");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Date prescriptionDate = Date.valueOf(request.getParameter("prescriptionDate"));
        String notes = request.getParameter("notes");

        Prescription newPrescription = new Prescription(patientName, doctorName, medicineName, dosage, quantity, prescriptionDate, notes);
        prescriptionDao.insertPrescription(newPrescription);
        response.sendRedirect("PrescriptionController?action=list");
    }

    private void updatePrescription(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String patientName = request.getParameter("patientName");
        String doctorName = request.getParameter("doctorName");
        String medicineName = request.getParameter("medicineName");
        String dosage = request.getParameter("dosage");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Date prescriptionDate = Date.valueOf(request.getParameter("prescriptionDate"));
        String notes = request.getParameter("notes");

        Prescription prescription = new Prescription(id, patientName, doctorName, medicineName, dosage, quantity, prescriptionDate, notes);
        prescriptionDao.updatePrescription(prescription);
        response.sendRedirect("PrescriptionController?action=list");
    }

    private void deletePrescription(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        prescriptionDao.deletePrescription(id);
        response.sendRedirect("PrescriptionController?action=list");
    }

    private void generatePrescription(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Prescription prescription = prescriptionDao.selectPrescription(id);
        request.setAttribute("patientName", prescription.getPatientName());
        request.setAttribute("doctorName", prescription.getDoctorName());
        request.setAttribute("medicineName", prescription.getMedicineName());
        request.setAttribute("dosage", prescription.getDosage());
        request.setAttribute("quantity", prescription.getQuantity());
        request.setAttribute("prescriptionDate", prescription.getPrescriptionDate());
        request.setAttribute("notes", prescription.getNotes());
        request.getRequestDispatcher("GeneratePrescription.jsp").forward(request, response);
    }

    private boolean isUserDoctor(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return user != null && "Doctor".equals(user.getRole());
    }
}

