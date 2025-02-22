// Add event listeners to all navbar links
document.addEventListener('DOMContentLoaded', function() {
    // Get all nav links that are inside the navbar-collapse menu
    const navLinks = document.querySelectorAll('.navbar-collapse .nav-link');
    
    // Get the navbar collapse element
    const navBarCollapse = document.querySelector('.navbar-collapse');
    
    // Add click handler to each nav link
    navLinks.forEach(function(link) {
        link.addEventListener('click', function() {
            // Check if the navbar collapse is expanded
            if (navBarCollapse.classList.contains('show')) {
                // Hide the navbar by clicking the navbar toggler
                document.querySelector('.navbar-toggler').click();
            }
        });
    });
});