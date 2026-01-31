// * 정렬

// https://www.acmicpc.net/problem/5052

#include <algorithm>
#include <iostream>
#include <string>
#include <vector>

int main(void) {
    int TCCount;
    std::cin >> TCCount;

    for (int TCIndex = 0; TCIndex < TCCount; TCIndex = TCIndex + 1) {
        int PNCount;
        std::cin >> PNCount;

        std::vector<std::string> PNList;
        PNList.clear();
        std::vector<std::string>().swap(PNList);

        for (int PNIndex = 0; PNIndex < PNCount; PNIndex = PNIndex + 1) {
            std::string PN;
            std::cin >> PN;

            PNList.push_back(PN);
        }

        sort(PNList.begin(), PNList.end());

        bool is_consistency = true;
        for (std::vector<std::string>::iterator iter = PNList.begin(); iter != PNList.end(); iter = iter + 1) {
            if (!(iter[0].length() < iter[1].length())) {
                continue;
            }
            int prefix_position = iter[1].find(iter[0]);
            if (prefix_position >= 0) {
                is_consistency = false;
                break;
            }
        }

        if (is_consistency) {
            std::cout << "YES" << std::endl;
        } else {
            std::cout << "NO" << std::endl;
        }
    }

    return 0;
}

// * 브루트포스

// https://www.acmicpc.net/problem/5052

// #include <iostream>
// #include <string>
// #include <vector>
// #include <iterator>

// int main(void)
// {

//     int TC_count = 0, phoneNumber_count = 0;

//     std::vector<std::string> phoneNumber_list;

//     std::cin >> TC_count;

//     for(int TC_index = 0; TC_index < TC_count; TC_index = TC_index + 1)
//     {
//         phoneNumber_list.clear();

//         std::cin >> phoneNumber_count;
//         for(int phoneNumber_index = 0; phoneNumber_index < phoneNumber_count; phoneNumber_index = phoneNumber_index +
//         1)
//         {
//             std::string curPhoneNumber;
//             std::cin >> curPhoneNumber;

//             phoneNumber_list.push_back(curPhoneNumber);
//         }

//         bool has_consistency = true;

//         for(std::vector<std::string>::iterator iterater_first = phoneNumber_list.begin(); iterater_first !=
//         phoneNumber_list.end(); iterater_first++)
//         {
//             for(std::vector<std::string>::iterator iterater_second = iterater_first + 1; iterater_second !=
//             phoneNumber_list.end(); iterater_second++)
//             {
//                 int first_index = 0, second_index = 0;
//                 bool has_prefix = true;

//                 while((*iterater_first)[first_index] != '\0' and (*iterater_second)[second_index] != '\0')
//                 {
//                     if((*iterater_first)[first_index] != (*iterater_second)[second_index])
//                     {
//                         has_prefix = false;
//                         break;
//                     }

//                     first_index++;
//                     second_index++;
//                 }

//                 if(has_prefix == true)
//                 {
//                     has_consistency = false;
//                     break;
//                 }
//             }

//             if(has_consistency == false)
//             {
//                 break;
//             }
//         }

//         if(has_consistency == true)
//         {
//             std::cout << "YES" << std::endl;
//         }
//         else
//         {
//             std::cout << "NO" << std::endl;
//         }
//     }

//     return 0;
// }
