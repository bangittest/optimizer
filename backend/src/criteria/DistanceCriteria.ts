// import { BaseCriteria } from './BaseCriteria';
// import { Context } from './Context';

// export class DistanceCriteria extends BaseCriteria {
//   constructor(weight: number) {
//     super('Distance', weight);
//   }

//   // Định nghĩa method calculate đúng kiểu TypeScript
//   calculate(context: Context): number {
   
//     // Chuẩn hóa khoảng cách, giả sử max 1000km
//     return 0;
//   }

//   private calculateDistance(lat1: number, lon1: number, lat2: number, lon2: number): number {
//     const R = 6371; // bán kính Trái đất (km)
//     const dLat = this.degToRad(lat2 - lat1);
//     const dLon = this.degToRad(lon2 - lon1);

//     const a =
//       Math.sin(dLat / 2) * Math.sin(dLat / 2) +
//       Math.cos(this.degToRad(lat1)) * Math.cos(this.degToRad(lat2)) *
//       Math.sin(dLon / 2) * Math.sin(dLon / 2);

//     const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//     return R * c;
//   }

//   private degToRad(deg: number): number {
//     return deg * (Math.PI / 180);
//   }
// }
