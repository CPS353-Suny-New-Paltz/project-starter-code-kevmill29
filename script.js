// script.js
// Visualizing Project Euler #27: Quadratic Primes

const DATA_FILE_PATH = 'data.csv'; 

async function loadDataAndRenderChart() {
    const statusElement = document.getElementById('data-status');

    try {
        const response = await fetch(DATA_FILE_PATH);
        if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);

        const text = await response.text();
        
        const labels = [];
        const dataValues = [];

        // 1. Parse the CSV specifically as "Input, Result" pairs
        const rows = text.trim().split('\n');
        
        rows.forEach(row => {
            const parts = row.split(',');
            if (parts.length >= 2) {
                // Column 0 is the Input (Label on X-Axis)
                labels.push(parts[0].trim()); 
                
                // Column 1 is the Result (Height of Bar)
                dataValues.push(Number(parts[1].trim())); 
            }
        });

        if (dataValues.length === 0) {
            statusElement.innerText = "File found, but it contains no valid data.";
            return;
        }

        statusElement.innerText = `Analyzed ${dataValues.length} coefficients for Prime Generation.`;
        renderChart(labels, dataValues);

    } catch (error) {
        console.error('Error fetching data:', error);
        statusElement.innerText = "Error: Could not load 'data.csv'. Ensure the Java Client has finished writing.";
        statusElement.style.color = "red";
    }
}

function renderChart(labels, data) {
    const ctx = document.getElementById('myChart').getContext('2d');

    // Destroy existing chart if it exists (prevents glitching when refreshing)
    if (window.myChartInstance) {
        window.myChartInstance.destroy();
    }

    window.myChartInstance = new Chart(ctx, {
        type: 'bar', // <--- Forced to BAR graph
        data: {
            labels: labels, // This will now show the actual input numbers (-61, 33, etc.)
            datasets: [{
                label: 'Consecutive Primes Found',
                data: data, // This will now ONLY be the results (positive numbers)
                backgroundColor: 'rgba(54, 162, 235, 0.6)', // Blue bars
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            plugins: {
                title: {
                    display: true,
                    text: 'Computation Engine Results',
                    font: { size: 18 }
                },
                tooltip: {
                    callbacks: {
                        title: function(context) {
                            return `Input Coefficient: ${context[0].label}`;
                        },
                        label: function(context) {
                            return `Primes Found: ${context.raw}`;
                        }
                    }
                }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: 'Sequence Length (n)'
                    }
                },
                x: {
                    title: {
                        display: true,
                        text: 'Input Coefficient (a)'
                    }
                }
            }
        }
    });
}

loadDataAndRenderChart();