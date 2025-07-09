       // Initialize charts
       function initCharts() {
           // Inventory Chart
           const inventoryCtx = document.getElementById('inventoryChart').getContext('2d');
           const inventoryChart = new Chart(inventoryCtx, {
               type: 'line',
               data: {
                   labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
                   datasets: [{
                       label: 'Blood In',
                       data: [120, 150, 180, 90, 130, 160, 190],
                       borderColor: '#28a745',
                       backgroundColor: 'rgba(40, 167, 69, 0.1)',
                       tension: 0.3,
                       fill: true
                   }, {
                       label: 'Blood Out',
                       data: [80, 110, 70, 120, 90, 140, 110],
                       borderColor: '#e53935',
                       backgroundColor: 'rgba(229, 57, 53, 0.1)',
                       tension: 0.3,
                       fill: true
                   }]
               },
               options: {
                   responsive: true,
                   plugins: {
                       legend: {
                           position: 'top',
                       }
                   },
                   scales: {
                       y: {
                           beginAtZero: true,
                           title: {
                               display: true,
                               text: 'Units'
                           }
                       }
                   }
               }
           });
           
           // Distribution Chart
           const distCtx = document.getElementById('distributionChart').getContext('2d');
           const distChart = new Chart(distCtx, {
               type: 'doughnut',
               data: {
                   labels: ['A+', 'B+', 'O+', 'AB+', 'A-', 'B-', 'O-', 'AB-'],
                   datasets: [{
                       data: [312, 198, 402, 74, 45, 32, 89, 16],
                       backgroundColor: [
                           '#e53935', '#ff9800', '#4caf50', '#2196f3',
                           '#c62828', '#ef6c00', '#2e7d32', '#1565c0'
                       ],
                       borderWidth: 0
                   }]
               },
               options: {
                   responsive: true,
                   plugins: {
                       legend: {
                           position: 'bottom',
                           labels: {
                               padding: 20,
                               boxWidth: 15
                           }
                       }
                   },
                   cutout: '70%'
               }
           });
       }
       
       // Event Listeners
       loginButton.addEventListener('click', login);
       logoutButton.addEventListener('click', logout);
       
       // Allow login with Enter key
       passwordInput.addEventListener('keypress', function(e) {
           if (e.key === 'Enter') {
               login();
           }
       });
       
       // Demo login for easier testing
       window.addEventListener('load', function() {
           usernameInput.value = 'admin';
           passwordInput.value = 'password';
       });