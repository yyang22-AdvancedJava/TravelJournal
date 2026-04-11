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

    // --- 진해진 색상 베리에이션 (날씨 전용) ---

    if (s.includes('sun') || s.includes('clear')) {
        // 맑음: 화사하고 진한 노랑
        body.style.backgroundColor = "#FFEB3B";
    }
    else if (s.includes('cloud') || s.includes('overcast')) {
        // 구름: 확실히 어두운 블루그레이
        body.style.backgroundColor = "#90A4AE";
    }
    else if (s.includes('mist') || s.includes('fog') || s.includes('haze')) {
        // 안개: 묵직한 중간 회색
        body.style.backgroundColor = "#BDBDBD";
    }
    else if (s.includes('rain') || s.includes('drizzle') || s.includes('patchy rain') || s.includes('shower')) {
        // 비: 분위기 있는 다크한 블루톤
        body.style.backgroundColor = "#78909C";
    }
    else if (s.includes('thunder') || s.includes('storm')) {
        // 폭풍: 강렬한 딥그레이
        body.style.backgroundColor = "#546E7A";
    }
    else if (s.includes('snow') || s.includes('ice') || s.includes('sleet')) {
        // 눈: 약간 푸른빛이 도는 밝은 회색
        body.style.backgroundColor = "#CFD8DC";
    }
    else {
        // 기본값: 선명한 연보라
        body.style.backgroundColor = "#D1C4E9";
    }
}