# 🔴 Pokédex App

Aplicativo Android que lista e exibe detalhes de Pokémons usando a [PokéAPI](https://pokeapi.co/).

Desenvolvido durante o curso **Android Avançado** da **Escola Nova Era Tech** com arquitetura **MVVM + Clean Architecture**.

---

## 📱 Download APK

**Opção mais rápida e direta:**

[![Download APK](https://img.shields.io/badge/Download-APK%20v1.0.0-green?style=for-the-badge&logo=android)](https://github.com/TallesGuerra/Pokedex/releases/tag/v1.0.0)

### **Como instalar:**
1. Clique no botão acima ou [acesse aqui](https://github.com/TallesGuerra/Pokedex/releases/tag/v1.0.0)
2. No seu Android, vá em **Configurações → Segurança → Fontes desconhecidas** e ative
3. Localize o arquivo `Pokedex-v1.0.0.apk` e toque para instalar
4. Abra e aproveite! 🎮

**Requisitos mínimos:**
- Android 8.0+ (API 26)
- ~[XX MB] de espaço

---

## 🎯 Funcionalidades

- ✅ **Lista de Pokémons** - Grid com paginação
- ✅ **Busca por Nome** - Filtrar pokémons em tempo real
- ✅ **Detalhe do Pokémon** - Stats, tipos, habilidades, altura, peso
- ✅ **Tratamento de Erros** - Com retry button
- ✅ **Tema Material 3** - Light e Dark mode
- ✅ **Testes Unitários** - Formatters, Mappers, ViewModels
- ✅ **Testes de UI** - Composables com Compose Test

---

## 🏗️ Arquitetura

```
MVVM + Clean Architecture

┌─────────────────────────────────────┐
│        UI LAYER (Presentation)      │
├─────────────────────────────────────┤
│  Composables (Screens, Components)  │
│  ViewModels (State Management)      │
│  UiState (Loading, Success, Error)  │
└────────────┬────────────────────────┘
             │
┌────────────▼────────────────────────┐
│      DOMAIN LAYER (Business)        │
├─────────────────────────────────────┤
│  Models (Pokemon, PokemonDetail)    │
│  Repository Interface               │
│  Use Cases (Future)                 │
└────────────┬────────────────────────┘
             │
┌────────────▼────────────────────────┐
│       DATA LAYER (Persistence)      │
├─────────────────────────────────────┤
│  Repository Implementation          │
│  API (Retrofit + OkHttp)            │
│  DTOs (Data Transfer Objects)       │
│  Mappers (DTO → Domain)             │
│  Room DB (Future - Favoritos)       │
└─────────────────────────────────────┘
```

---

## 📁 Estrutura de Pastas

```
app/src/
├── main/java/com/example/pokedex/
│   ├── data/
│   │   ├── api/
│   │   │   ├── PokéApi.kt           # Interface Retrofit
│   │   │   └── RetrofitClient.kt    # Singleton Retrofit
│   │   ├── dto/
│   │   │   ├── PokemonDto.kt
│   │   │   └── PokemonDetailDto.kt
│   │   ├── mapper/
│   │   │   ├── PokemonMapper.kt
│   │   │   └── PokemonDetailMapper.kt
│   │   ├── repository/
│   │   │   └── PokemonRepositoryImpl.kt
│   │   ├── db/                      # Future: Room
│   │   └── dao/
│   ├── domain/
│   │   ├── model/
│   │   │   ├── Pokemon.kt
│   │   │   ├── PokemonDetail.kt
│   │   │   └── Stat.kt
│   │   └── repository/
│   │       └── PokemonRepository.kt
│   ├── presentation/
│   │   ├── ui/
│   │   │   ├── screens/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── SplashScreen.kt
│   │   │   │   ├── PokemonListScreen.kt
│   │   │   │   └── PokemonDetailScreen.kt
│   │   │   ├── theme/
│   │   │   │   ├── Color.kt
│   │   │   │   ├── Typography.kt
│   │   │   │   └── Theme.kt
│   │   │   └── state/
│   │   │       └── UiState.kt
│   │   └── viewmodel/
│   │       ├── PokemonListViewModel.kt
│   │       └── PokemonDetailViewModel.kt
│   └── utils/
│       ├── Constants.kt
│       ├── Extensions.kt
│       ├── Formatters.kt
│       ├── ImageMapper.kt
│       └── test/
│           ├── FakePokemonRepository.kt
│           ├── FakePokeApi.kt
│           ├── MainDispatcherRule.kt
│           └── TestDispatchers.kt
│
├── test/java/com/example/pokedex/    # Testes Unitários
│   ├── utils/FormattersTest.kt
│   ├── data/mapper/PokemonMapperTest.kt
│   ├── data/repository/PokemonRepositoryTest.kt
│   └── presentation/viewmodel/PokemonListViewModelTest.kt
│
└── androidTest/java/com/example/pokedex/  # Testes UI
    └── presentation/ui/
        ├── PokemonListScreenTest.kt
        └── PokemonDetailScreenTest.kt
```

---

## 🛠️ Tecnologias Utilizadas

### **UI**
- Jetpack Compose
- Material Design 3
- Navigation Compose

### **Networking**
- Retrofit 2.9.0
- OkHttp 4.11.0
- Gson (JSON parsing)

### **Arquitetura**
- ViewModel (Lifecycle)
- LiveData + StateFlow
- Repository Pattern
- Dependency Injection (Future: Hilt)

### **Image Loading**
- Coil 2.4.0

### **Database**
- Room (Future)

### **Testes**
- JUnit 4
- Mockito-Kotlin
- Espresso
- Compose Test
- Truth (Assertions)
- Coroutines Test

### **Build**
- Gradle 9.4.1
- Kotlin 2.2.10
- Android SDK 36

---

## 🚀 Como Rodar (Desenvolvedor)

### **Pré-requisitos**
- Android Studio 2024.1+
- JDK 11+
- Android SDK 36+

### **Instalação**

1. **Clone o repositório**
```bash
git clone https://github.com/TallesGuerra/Pokedex.git
cd Pokedex
```

2. **Abra no Android Studio**
```bash
# Ou use Android Studio diretamente
open -a "Android Studio" .
```

3. **Build do projeto**
```bash
./gradlew clean build
```

4. **Execute no emulador/device**
```bash
./gradlew installDebug
```

Ou clique em **Run** no Android Studio (Shift+F10)

---

## 🧪 Testes

### **Testes Unitários** (rápidos, sem emulador)
```bash
./gradlew test
```

Verifica:
- ✅ Formatação de dados (altura, peso, ID)
- ✅ Mapeamento DTO → Domain
- ✅ Repository com mock da API
- ✅ ViewModel e transformação de estados

### **Testes de UI** (no emulador)
```bash
./gradlew connectedAndroidTest
```

Verifica:
- ✅ Header "Pokédex" aparece
- ✅ Search bar funciona
- ✅ Lista de pokémons exibe
- ✅ Clique em pokémon navega para detalhe
- ✅ Tela de detalhe mostra stats e habilidades
- ✅ Botão voltar funciona

### **Cobertura de Testes**
```bash
./gradlew testDebugUnitTest --tests "com.example.pokedex.*"
```

---

## 📊 Fluxo de Dados

```
Usuario abre app
    ↓
MainActivity inicia (Splash Screen)
    ↓
Navega para PokemonListScreen (2 segundos)
    ↓
viewModel.loadPokemons() é chamado
    ↓
Repository.getPokemons() chama API
    ↓
RetrofitClient.api faz GET /pokemon?limit=20
    ↓
PokemonListResponse (DTO) retorna
    ↓
PokemonDetailMapper.toDomain() converte
    ↓
formatHeight(), formatWeight(), formatId() formatam dados
    ↓
Repository retorna Result.Success<List<Pokemon>>
    ↓
ViewModel mapeia para UiState.Success
    ↓
LiveData.value atualiza
    ↓
Composable observa e renderiza grid
    ↓
Usuario vê lista de pokémons ✅
```

---

## 🔌 API Integration

### **PokéAPI**
Base URL: `https://pokeapi.co/api/v2/`

**Endpoints usados:**

```
GET /pokemon?limit=20&offset=0
↳ Retorna lista paginada de pokémons

GET /pokemon/{id}
↳ Retorna detalhes completo (stats, tipos, habilidades)

GET /pokemon/{name}
↳ Busca pokémon por nome
```

**Exemplo de resposta:**
```json
{
  "id": 25,
  "name": "pikachu",
  "height": 4,
  "weight": 60,
  "types": [{"type": {"name": "electric"}}],
  "stats": [{"stat": {"name": "hp"}, "base_stat": 35}],
  "abilities": [{"ability": {"name": "static"}}]
}
```

---

## 🎨 Tema Material 3

### **Cores Principais**
- Primary: `#FF6B6B` (Vermelho Pokédex)
- Secondary: `#1E90FF` (Azul)
- Background: `#FAFAFA`
- Surface: `#FFFFFF`

### **Cores por Tipo de Pokémon**
- Electric: `#F8D030`
- Grass: `#78C850`
- Water: `#6890F0`
- Fire: `#F08030`
- [... mais 15 tipos]

### **Tipografia Material 3**
- Headlines: Bold (28-57sp)
- Titles: SemiBold (16-22sp)
- Body: Regular (12-16sp)
- Labels: Medium (11-14sp)

---

## 🚀 Próximas Melhorias

- [ ] **Room Database** - Cache offline com favoritos
- [ ] **Hilt Dependency Injection** - Injeção automática
- [ ] **Pagination** - Infinite scroll
- [ ] **Filtro por Tipo** - Filter chips
- [ ] **Zoom de Imagem** - PinchZoom
- [ ] **Animation** - Transições entre telas
- [ ] **ProGuard** - Ofuscação de código
- [ ] **Widgets** - Widget de pokémon aleatório

---

## 👨‍💻 Autor

**Talles Guerra**
- 📧 Email: [seu-email]
- 🔗 LinkedIn: [linkedin.com/in/talles-guerra](https://linkedin.com/in/talles-guerra)
- 💻 GitHub: [@TallesGuerra](https://github.com/TallesGuerra)
- 🌐 Portfolio: [tallesguerra.github.io/pagina-portfolio](https://tallesguerra.github.io/pagina-portfolio)

---

## 🏫 Mentoria

Projeto desenvolvido sob mentoria de **Roque Buarque Junior** durante o curso **Android Avançado** da **Escola Nova Era Tech**.

---

## 📄 Licença

Este projeto está sob licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

---

## 📞 Suporte

Dúvidas ou sugestões? Abra uma [Issue](https://github.com/TallesGuerra/Pokedex/issues) no GitHub!

---

**Desenvolvido com ❤️ para aprender Android Avançado**
