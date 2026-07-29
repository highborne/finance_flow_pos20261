<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a id="readme-top"></a>

<!-- PROJECT SHIELDS -->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/highborne/finance_flow_pos20261">
    <img src="https://raw.githubusercontent.com/highborne/finance_flow_pos20261/main/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">FinanceFlow</h3>

  <p align="center">
    A modern Android application for personal finance control and cash flow management.
    <br />
    <a href="https://github.com/highborne/finance_flow_pos20261"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/highborne/finance_flow_pos20261">View Demo</a>
    &middot;
    <a href="https://github.com/highborne/finance_flow_pos20261/issues">Report Bug</a>
    &middot;
    <a href="https://github.com/highborne/finance_flow_pos20261/issues">Request Feature</a>
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project

FinanceFlow is an Android application designed to help users track their daily financial transactions. It provides a simple and efficient way to register income and expenses, offering a clear view of the total balance and recent activities.

Key features:
* **MVVM Architecture**: Clean and maintainable code structure.
* **Jetpack Compose**: Modern declarative UI toolkit.
* **Firebase Firestore**: Real-time data persistence and synchronization.
* **Dynamic Dashboard**: Summary of total balance, income, and expenses.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

* [![Kotlin][Kotlin-badge]][Kotlin-url]
* [![Jetpack Compose][Compose-badge]][Compose-url]
* [![Firebase][Firebase-badge]][Firebase-url]
* [![Material 3][Material3-badge]][Material3-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

* Android Studio Ladybug or newer.
* Android SDK 35+.
* A Firebase project with Firestore enabled.

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/highborne/finance_flow_pos20261.git
   ```
2. Add your `google-services.json` file to the `app/` directory.
3. Build the project in Android Studio.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->
## Usage

1. **Dashboard**: View your total balance and recent transactions.
2. **Add Transaction**: Click the FAB (+) to register a new income or expense.
3. **Validation**: The app ensures all fields are correctly filled before saving.
4. **Real-time**: Your data is synchronized instantly with Firebase.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ROADMAP -->
## Roadmap

- [x] Basic Transaction Registration
- [x] Firestore Integration
- [x] MVVM Architecture
- [x] Brazilian Real (BRL) Formatting

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

Everton k. - [@evertonlc](https://www.instagram.com/evertonlc)

Project Link: [https://github.com/highborne/finance_flow_pos20261](https://github.com/highborne/finance_flow_pos20261)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* [Best-README-Template](https://github.com/othneildrew/Best-README-Template)
* [Android Developers Documentation](https://developer.android.com/)
* [Firebase Documentation](https://firebase.google.com/docs)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
[contributors-shield]: https://img.shields.io/github/contributors/esomakers/FinanceFlow.svg?style=for-the-badge
[contributors-url]: https://github.com/esomakers/FinanceFlow/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/esomakers/FinanceFlow.svg?style=for-the-badge
[forks-url]: https://github.com/esomakers/FinanceFlow/network/members
[stars-shield]: https://img.shields.io/github/stars/esomakers/FinanceFlow.svg?style=for-the-badge
[stars-url]: https://github.com/esomakers/FinanceFlow/stargazers
[issues-shield]: https://img.shields.io/github/issues/esomakers/FinanceFlow.svg?style=for-the-badge
[issues-url]: https://github.com/esomakers/FinanceFlow/issues
[license-shield]: https://img.shields.io/github/license/esomakers/FinanceFlow.svg?style=for-the-badge
[license-url]: https://github.com/esomakers/FinanceFlow/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/esomakers
[Kotlin-badge]: https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white
[Kotlin-url]: https://kotlinlang.org/
[Compose-badge]: https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white
[Compose-url]: https://developer.android.com/jetpack/compose
[Firebase-badge]: https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black
[Firebase-url]: https://firebase.google.com/
[Material3-badge]: https://img.shields.io/badge/Material%203-757575?style=for-the-badge&logo=materialdesign&logoColor=white
[Material3-url]: https://m3.material.io/
