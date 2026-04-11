/**
 * City 입력창에서 포커스가 벗어날 때 실행되는 함수
 */
function findWeather() {
    const cityInput = document.getElementById('city');
    const weatherHiddenInput = document.getElementById('weatherValue');
    const weatherDisplay = document.getElementById('weatherDisplay');

    // HTML(JSP)의 데이터 속성에서 설정값 읽어오기
    const configElement = document.getElementById('weather-api-config');
    if (!configElement) return;

    const config = configElement.dataset;
    const url = config.url;
    const key = config.key;

    if (cityInput.value && url && key) {
        // API 호출 (weatherapi.com 구조)
        fetch(`${url}?key=${key}&q=${cityInput.value}&aqi=no`)
            .then(response => {
                if (!response.ok) throw new Error('City not found');
                return response.json();
            })
            .then(data => {
                if (data.current && data.current.condition) {
                    const status = data.current.condition.text;

                    // 1. DB 저장을 위한 Hidden input 세팅
                    if(weatherHiddenInput) weatherHiddenInput.value = status;

                    // 2. 화면에 텍스트 표시
                    weatherDisplay.innerText = `Current Weather: ${status}`;
                    weatherDisplay.classList.remove('d-none');

                    // 3. 배경색 변경 함수 호출
                    changeBgByWeather(status);
                }
            })
            .catch(err => {
                console.error("Weather API Error:", err);
                weatherDisplay.innerText = "Weather info not available";
                weatherDisplay.classList.remove('d-none');
            });
    }
}

/**
 * 날씨 상태에 따라 배경색을 부드럽게 변경
 */
function changeBgByWeather(status) {
    const body = document.body;
    body.style.transition = "background-color 0.8s ease";
    const s = status.toLowerCase();

    // 날씨 키워드별 색상 매칭
    if (s.includes('sun') || s.includes('clear')) {
        body.style.backgroundColor = "#e0f7fa"; // 맑음: 연하늘
    } else if (s.includes('cloud') || s.includes('overcast') || s.includes('mist')) {
        body.style.backgroundColor = "#f5f5f5"; // 구름/안개: 연회색
    } else if (s.includes('rain') || s.includes('drizzle') || s.includes('shower')) {
        body.style.backgroundColor = "#d1d9e6"; // 비: 블루그레이
    } else if (s.includes('snow') || s.includes('ice')) {
        body.style.backgroundColor = "#ffffff"; // 눈: 흰색
    } else {
        body.style.backgroundColor = "#F8F6FF"; // 기본: 연보라
    }
}